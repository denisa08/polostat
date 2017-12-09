package ru.denisa.service;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;
import ru.denisa.dao.PairDAO;
import ru.denisa.dao.SequenceDAO;
import ru.denisa.model.BittrexPair;
import ru.denisa.model.Pair;
import ru.denisa.service.bittrex.utils.Bittrex;
import ru.denisa.service.util.PairCreator;
import ru.denisa.service.util.observer.ISubject;
import ru.denisa.telegram.observer.ITelegramObserver;
import ru.denisa.telegram.observer.TelegramObserver;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by root on 05/08/17.
 */
@Slf4j
@Component
@Profile({"dev"})
public class DevBittrexRunner implements ISubject {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private PairDAO pairRepo;
    @Autowired
    private SequenceDAO sequenceDAO;

    @Autowired
    MongoOperations mongoOperations;


    @Autowired
    PairCreator pairCreator;


    Bittrex wrapper = new Bittrex();


    private void loadMarket() {

        String rawResponse = wrapper.getMarketSummaries();
        List<HashMap<String, String>> responseMapList = Bittrex.getMapsFromResponse(rawResponse);
        for (HashMap<String, String> map : responseMapList) {
            if (map.get("MarketName").substring(0, 3).equals("BTC")) {
                BittrexPair bittrexPair = new BittrexPair();
                bittrexPair.setId(sequenceDAO.getNextSequenceId("pairs"));
                bittrexPair.setName(map.get("MarketName"));
                pairRepo.save(bittrexPair);

            }
        }
    }


    public void getLastMarket() {
        String rawResponse = wrapper.getMarketSummaries();

        log.info("BittrexRunner, going to the bittrex.com, time {}", dateFormat.format(new Date()));
        List<HashMap<String, String>> responseMapList = Bittrex.getMapsFromResponse(rawResponse);
        log.info("BittrexRunner, just went to the bittrex.com, time {}", dateFormat.format(new Date()));
        for (HashMap<String, String> map : responseMapList) {
            if (map.get("MarketName").substring(0, 3).equals("BTC")) {

                Pair savedPair = pairCreator.createAndSavePair(map);
                log.info("saved pair"+map.get("MarketName")+", time {}", dateFormat.format(new Date()));

                Runnable task = () -> {
                    notifyObserver(savedPair);
                };
                Thread thread = new Thread(task);
                thread.start();
            }
        }
        if (!pairRepo.checkBitrexPairs()) {
            loadMarket();
        }
        log.info("BittrexRunner, saved pairs to DB, time {}", dateFormat.format(new Date()));
    }


    @Override
    public void register(ITelegramObserver observer) {

    }

    @Override
    public void unregister(ITelegramObserver observer) {

    }

    @Override
    public void notifyObserver(Pair pair) {

    }
}
