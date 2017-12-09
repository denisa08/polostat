package ru.denisa.rest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.denisa.dao.PairDAO;
import ru.denisa.model.BittrexPair;
import ru.denisa.model.Pair;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by d.aleksandrov on 24.08.2017.
 */


@RestController
public class PairRestService {

    @Autowired
    private PairDAO pairDAO;


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


}
