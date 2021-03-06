package ru.denisa.service.tasks;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.denisa.bittrex.dao.PairDAO;
import ru.denisa.bittrex.dao.db.SequenceDAO;
import ru.denisa.service.runners.DevBittrexRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by root on 12/07/17.
 */
@Component
@Configurable
@Profile({"dev"})

public class DevSheduledTask {


    private final static Logger LOG = LogManager.getLogger(DevSheduledTask.class);
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ScheduledTask.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Autowired
    private PairDAO pairRepo;
    @Autowired
    private SequenceDAO sequenceDAO;

    @Autowired
    private DevBittrexRunner bittrexRunner;


    // @Scheduled(fixedRate = 300000)
    @Scheduled(fixedRate = 10000)
    public synchronized void go() throws Exception {
        log.info("The time is now {}", dateFormat.format(new Date()));
        bittrexRunner.getLastMarket();
    }




}
