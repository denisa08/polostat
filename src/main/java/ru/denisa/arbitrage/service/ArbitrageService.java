package ru.denisa.arbitrage.service;

import com.cf.data.model.poloniex.PoloniexTicker;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.denisa.bittrex.dao.PairDAO;
import ru.denisa.arbitrage.model.ArbitragePair;
import ru.denisa.bittrex.model.Pair;
import ru.denisa.poloniex.dao.PoloniexDao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
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

    private ArrayList<Pair> bittrexPairs;
    private ArrayList<PoloniexTicker> poloniexTickers;
    ExecutorService executor = Executors.newSingleThreadExecutor();


    private ArrayList<ArbitragePair> pairs;

    Future<?> future;


    //Реализовать callable


    public ArrayList<ArbitragePair> getPairs() {

        ArrayList<ArbitragePair> arbitragePairs = new ArrayList<>();
        if (future == null || !future.isDone()) {
            //получаем пары из битрекса
            future = executor.submit(() -> {
                poloniexTickers = poloniexDao.getTickers();


            });
        }

        if (poloniexTickers != null) {

            poloniexTickers.forEach(poloniexTicker -> {


                ArbitragePair arbitragePair = new ArbitragePair();

                Pair pair = pairDAO.getLastPair(poloniexTicker.getName());
                if (pair != null) {
                    arbitragePair.setName(poloniexTicker.getName());
                    arbitragePair.setLastPoloPrice(poloniexTicker.getLast());
                    arbitragePair.setLastBtxPrice(pair.getLast());
                     arbitragePair.setLastPoloVolume(poloniexTicker.getQuoteVolume().doubleValue());
                    arbitragePair.setLastBtxVolume(pair.getVolume());
                    arbitragePair.setPriceDiff(getPriceChange(pair.getLast(), poloniexTicker.getLast()));
                    arbitragePairs.add(arbitragePair);
                }


            });

            future = null;


        }


        return arbitragePairs;


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


    private Double truncatedDouble(Double value) {
        Double truncatedDouble = null;

        try {
            truncatedDouble = BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();

        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }

        return truncatedDouble;

    }


}
