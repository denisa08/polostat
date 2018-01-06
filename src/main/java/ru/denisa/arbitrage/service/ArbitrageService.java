package ru.denisa.arbitrage.service;

import com.cf.data.model.poloniex.PoloniexTicker;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.denisa.dao.PairDAO;
import ru.denisa.model.ArbitragePair;
import ru.denisa.model.Pair;
import ru.denisa.poloniex.service.PoloniexRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * Created by d.aleksandrov on 04.01.2018.
 */

@Data
@Slf4j
@Component
public class ArbitrageService {

    @Autowired
    PoloniexRunner poloniexRunner;

    @Autowired
    private PairDAO pairDAO;


    private ArrayList<Pair> bittrexPairs;
    private ArrayList<PoloniexTicker> poloniexTickers;


    private ArrayList<ArbitragePair> pairs;


    public ArrayList<ArbitragePair> getPairs() {

        //получаем пары из полоникса
        poloniexTickers = poloniexRunner.getTickers();

        poloniexTickers.forEach(poloniexTicker -> {
            ArbitragePair arbitragePair = new ArbitragePair();


            //получаем пары из битрекса
            Pair pair = pairDAO.getLastPair(poloniexTicker.getName());



            arbitragePair.setName(poloniexTicker.getName());
            arbitragePair.setLastBtxPrice(pair.getLast());
            arbitragePair.setLastBtxVolume(poloniexTicker.getLast());
            arbitragePair.setLastPoloVolume(poloniexTicker.getQuoteVolume().doubleValue());
            arbitragePair.setLastBtxVolume(pair.getVolume());
            arbitragePair.setPriceDiff( getPriceChange(pair.getLast(),poloniexTicker.getLast()));


        });

        return null;


    }


    private Double getPriceChange(Double one, Double two) {
        Double newValue = null;

        try {
            newValue = ((two - one) / one) * 100;
            return truncatedDouble(newValue);

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

        return null;


    }


    private Double truncatedDouble(Double value) {
        Double truncatedDouble = null;

        try {
            truncatedDouble = BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
            return truncatedDouble;

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

        return null;
    }


}
