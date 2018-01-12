package ru.denisa.service.rest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.denisa.arbitrage.service.ArbitrageService;
import ru.denisa.bittrex.dao.PairDAO;
import ru.denisa.arbitrage.model.ArbitragePair;
import ru.denisa.bittrex.model.BittrexPair;
import ru.denisa.bittrex.model.Pair;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by d.aleksandrov on 24.08.2017.
 */


@RestController
public class PairRestService {

    @Autowired
    private PairDAO pairDAO;

    @Autowired
    private ArbitrageService  arbitrageService;


    private static final org.slf4j.Logger log = LoggerFactory.getLogger(PairRestService.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");





    @RequestMapping(path = "/pairs", method = RequestMethod.GET)
    public  List<Pair> query() {
        List<Pair> pairs = new ArrayList<>();
        for (BittrexPair bittrexPair : pairDAO.getBittrexPairs()) {
            Pair pair = pairDAO.getLastPair(bittrexPair.getName());
            if (pair != null) {
                pairs.add(pair);
            }

        }
        return pairs;
    }

    /*88
    Арбитражный метод поло-битра
     */


    @RequestMapping(path = "/polobtx", method = RequestMethod.GET)
    public List<ArbitragePair> polobtx() {

        return  arbitrageService.getPairs();

    }




}
