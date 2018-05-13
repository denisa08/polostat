package ru.denisa.arbitrage.service;

import com.cf.data.model.poloniex.PoloniexTicker;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.denisa.bittrex.dao.PairDAO;
import ru.denisa.arbitrage.model.ArbitragePair;
import ru.denisa.bittrex.model.Pair;
import ru.denisa.cryptopia.CryptopiaDataService;
import ru.denisa.poloniex.dao.PoloniexDao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.*;

/**
 * Created by d.aleksandrov on 04.01.2018.
 */

@Data
@Slf4j
@Component
public class ArbitrageService {


    @Autowired
    PoloniexDao poloniexDao;

    @Autowired
    PairDAO pairDAO;

    @Autowired
    CryptopiaDataService cryptopiaDataService;

    private ArrayList<Pair> bittrexPairs;
    private ArrayList<PoloniexTicker> poloniexTickers;
    private LinkedList<Ticker> cryptopiaTickers;



    ExecutorService executor = Executors.newCachedThreadPool();


    private ArrayList<ArbitragePair> pairs;

    Future<?> futurePolo;

    Future<?> futureCryptoPia;

    Future<?> futureBTX;


    private void initLastArbBtxPairs() {


        if (futureBTX == null || !futureBTX.isDone()) {
            //получаем пары из поло
            futureBTX = executor.submit(() -> {
                bittrexPairs = pairDAO.getLastArbitragePairs();


            });
        }
        if (bittrexPairs != null && bittrexPairs.size() > 0) {
            futureBTX = null;
        }

    }


    private void initArbPoloPairs() {

        if (futurePolo == null || !futurePolo.isDone()) {
            //получаем пары из поло
            futurePolo = executor.submit(() -> {
                poloniexTickers = poloniexDao.getTickers();


            });
        }


        if (poloniexTickers != null && poloniexTickers.size() > 0) {
            futurePolo = null;
        }
    }


    private void initArbCryptopiaPairs() {


        if (futureCryptoPia == null || !futureCryptoPia.isDone()) {
            //получаем пары из поло
            futureCryptoPia = executor.submit(() -> {
                cryptopiaTickers = cryptopiaDataService.getArbitrageLastTickers();


            });
        }


        if (cryptopiaTickers != null && cryptopiaTickers.size() > 0) {
            futureCryptoPia = null;
        }


    }


    //Реализовать callable


    public ArrayList<ArbitragePair> getPairs() {

        initLastArbBtxPairs();
        initArbCryptopiaPairs();
        initArbPoloPairs();
        ArrayList<ArbitragePair> arbitragePairs = new ArrayList<>();


        if (bittrexPairs == null || poloniexTickers == null || cryptopiaTickers == null) {
            return arbitragePairs;
        }


        for (int i = 0; i < cryptopiaTickers.size(); i++) {
            Ticker ticker = cryptopiaTickers.get(i);
            ArbitragePair arbitragePair = new ArbitragePair();
            arbitragePair.setLastCryptoPiaPrice(truncatedDouble(ticker.getLast().doubleValue()));
            arbitragePair.setLastBtxPrice(truncatedDouble(bittrexPairs.get(i).getLast()));
            arbitragePair.setLastPoloPrice(truncatedDouble(poloniexTickers.get(i).getLast()));
            arbitragePair.setName(bittrexPairs.get(i).getName());

            arbitragePairs.add(arbitragePair);
        }


        arbitragePairs = minMax(arbitragePairs);

        return arbitragePairs;


    }


    private ArrayList<ArbitragePair> minMax(ArrayList<ArbitragePair> arbitragePairs) {
        for (int i = 0; i < arbitragePairs.size(); i++) {

            double cryptopialast = arbitragePairs.get(i).getLastCryptoPiaPrice();
            double btxLast = arbitragePairs.get(i).getLastBtxPrice();
            double poloLast = arbitragePairs.get(i).getLastPoloPrice();


            //find max
            if (cryptopialast >= btxLast && cryptopialast >= poloLast) {
                 arbitragePairs.get(i).setMax(cryptopialast);
                 arbitragePairs.get(i).setBuyIn("Cryptopia");
            }

            //find max
            if (btxLast >= cryptopialast && btxLast >= poloLast) {
                 arbitragePairs.get(i).setMax(btxLast);
                 arbitragePairs.get(i).setBuyIn("Bittrex");
            }


            //find max
            if (poloLast >= cryptopialast && poloLast >= btxLast) {
                 arbitragePairs.get(i).setMax(poloLast);
                 arbitragePairs.get(i).setBuyIn("Poloniex");
            }


            //find min
            if (cryptopialast <= btxLast && cryptopialast <= poloLast) {
                 arbitragePairs.get(i).setMin(cryptopialast);
                arbitragePairs.get(i).setSellIn("Cryptopia");
            }

            //find max
            if (btxLast <= cryptopialast && btxLast <= poloLast) {
                 arbitragePairs.get(i).setMin(btxLast);
                 arbitragePairs.get(i).setSellIn("Bittrex");
            }


            //find max
            if (poloLast <= cryptopialast && poloLast <= btxLast) {
                 arbitragePairs.get(i).setMin(poloLast);
                 arbitragePairs.get(i).setSellIn("Poloniex");
            }

             arbitragePairs.get(i).setProfit(truncatedPrice(getPriceChange( arbitragePairs.get(i).getMin(),  arbitragePairs.get(i).getMax())));
        }
        return  arbitragePairs;

    }


    private Double getPriceChange(Double one, Double two) {
        Double newValue = null;

        try {
            newValue = ((two - one) / one) * 100;

        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }

        return truncatedDouble(newValue);


    }

    private Double truncatedPrice(Double value) {
        Double truncatedDouble = null;

        try {
            truncatedDouble = BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();

        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }

        return truncatedDouble;

    }


    private Double truncatedDouble(Double value) {
        Double truncatedDouble = null;

        try {
            truncatedDouble = BigDecimal.valueOf(value).setScale(6, RoundingMode.HALF_UP).doubleValue();

        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }

        return truncatedDouble;

    }


}
