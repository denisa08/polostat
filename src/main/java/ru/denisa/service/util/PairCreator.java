package ru.denisa.service.util;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.denisa.dao.PairDAO;
import ru.denisa.dao.SequenceDAO;
import ru.denisa.model.Pair;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by d.aleksandrov on 02.11.2017.
 */
@Component
public class PairCreator {

    private static PairCreator instance;

    @Autowired
    private PairDAO pairRepo;
    @Autowired
    private SequenceDAO sequenceDAO;

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(PairCreator.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    public Pair createAndSavePair(HashMap<String, String> paramMap) {

        Double last = Double.parseDouble(paramMap.get("Last"));
        Double low = Double.parseDouble(paramMap.get("Low"));
        Double high = Double.parseDouble(paramMap.get("High"));
        Double volume = Double.parseDouble(paramMap.get("Volume"));
        Double baseVolume = Double.parseDouble(paramMap.get("BaseVolume"));
        String pairName = paramMap.get("MarketName");


        Pair pair = new Pair();
       // log.info("get sequence time {}", dateFormat.format(new Date()));
        pair.setId(sequenceDAO.getNextSequenceId("pairs"));
      //  log.info("OK: get sequence time {}", dateFormat.format(new Date()));

        pair.setName(pairName);
        pair.setDate(ZonedDateTime.now(ZoneOffset.UTC).toEpochSecond());
        pair.setHigh(high);
        pair.setLow(low);
        pair.setLast(last);
        pair.setVolume(volume);
        pair.setBaseVolume(baseVolume);
        //check if this first record
       // log.info("get LAST PAIR time {}", dateFormat.format(new Date()));

        Pair lastPair = pairRepo.getLastPair(pairName);
       // log.info("OK: get LAST PAIR time {}", dateFormat.format(new Date()));


        if (lastPair != null) {
           // log.info("getFirstPair2Minutes time {}", dateFormat.format(new Date()));
            Pair twominutesPair = pairRepo.getFirstPair2Minutes(pairName);
           // log.info("OK:getFirstPair2Minutes time {}", dateFormat.format(new Date()));

           // log.info("getFirstPair3Minutes time {}", dateFormat.format(new Date()));
            Pair threeminutesPair = pairRepo.getFirstPair3Minutes(pairName);
           // log.info("OK:getFirstPair3Minutes time {}", dateFormat.format(new Date()));

           // log.info("getFirstPair4Minutes time {}", dateFormat.format(new Date()));
            Pair fourminutesPair = pairRepo.getFirstPair4Minutes(pairName);
          //  log.info("OK:getFirstPair4Minutes time {}", dateFormat.format(new Date()));

         //   log.info("getFirstPair5Minutes time {}", dateFormat.format(new Date()));
            Pair fminutesPair = pairRepo.getFirstPair5Minutes(pairName);
          //  log.info("OK:getFirstPair5Minutes time {}", dateFormat.format(new Date()));

           // log.info("getFirstPair10Minutes time {}", dateFormat.format(new Date()));
            Pair tenMinutesPair = pairRepo.getFirstPair10Minutes(pairName);
           // log.info("OK:getFirstPair10Minutes time {}", dateFormat.format(new Date()));

         //   log.info("getFirstPair30Minutes time {}", dateFormat.format(new Date()));
            Pair thyMinutesPair = pairRepo.getFirstPair30Minutes(pairName);
          //  log.info("OK:getFirstPair30Minutes time {}", dateFormat.format(new Date()));

          //  log.info("getFirstPair1Hour time {}", dateFormat.format(new Date()));
            Pair hourPair = pairRepo.getFirstPair1Hour(pairName);
           // log.info("OK:getFirstPair1Hour time {}", dateFormat.format(new Date()));

         //   log.info("getFirstPair3Hours time {}", dateFormat.format(new Date()));
            Pair threeHoursPair = pairRepo.getFirstPair3Hours(pairName);
          //  log.info("OK:getFirstPair3Hours time {}", dateFormat.format(new Date()));

         //   log.info("getFirstPair6Hours time {}", dateFormat.format(new Date()));
            Pair sixHoursPair = pairRepo.getFirstPair6Hours(pairName);
          //  log.info("OK:getFirstPair6Hours time {}", dateFormat.format(new Date()));

          //  log.info("getFirstPair24Hours time {}", dateFormat.format(new Date()));
            Pair tfHoursPair = pairRepo.getFirstPair24Hours(pairName);
          //  log.info("OK:getFirstPair24Hours time {}", dateFormat.format(new Date()));

          //  log.info("getFirstPairAllTime time {}", dateFormat.format(new Date()));
            Pair alltimePair = pairRepo.getFirstPairAllTime(pairName);
          //  log.info("OK:getFirstPairAllTime time {}", dateFormat.format(new Date()));

          //  log.info("set coefficents.. time {}", dateFormat.format(new Date()));

            pair.setOneChanged((getPriceChange(lastPair.getLast(), last)));
            pair.setOneChangedVolume(getVolumeChange(lastPair.getVolume(), volume));


            if (twominutesPair != null && twominutesPair.getLast() != null) {
                pair.setTwoChanged(getPriceChange(twominutesPair.getLast(), last));
                pair.setTwoChangedVolume(getVolumeChange(twominutesPair.getVolume(), volume));
            }

            if (threeminutesPair != null && threeminutesPair.getLast() != null) {
                pair.setThreeChanged(getPriceChange(threeminutesPair.getLast(), last));
                pair.setThreeChangedVolume(getVolumeChange(threeminutesPair.getVolume(), volume));
            }

            if (fourminutesPair != null && fourminutesPair.getLast() != null) {
                pair.setFourChanged(getPriceChange(fourminutesPair.getLast(), last));
                pair.setFourChangedVolume(getVolumeChange(fourminutesPair.getVolume(), volume));
            }


            if (fminutesPair != null && fminutesPair.getLast() != null) {
                pair.setFiveChanged(getPriceChange(fminutesPair.getLast(), last));
                pair.setFiveChangedVolume(getVolumeChange(fminutesPair.getVolume(), volume));


            }
            if (tenMinutesPair != null && tenMinutesPair.getLast() != null) {
                pair.setTenChanged(getPriceChange(tenMinutesPair.getLast(), last));
                pair.setTenChangedVolume(getVolumeChange(tenMinutesPair.getVolume(), volume));


            }
            if (thyMinutesPair != null && thyMinutesPair.getLast() != null) {
                pair.setThirtyChanged(getPriceChange(thyMinutesPair.getLast(), last));
                pair.setThirtyChangedVolume(getVolumeChange(thyMinutesPair.getVolume(), volume));


            }
            if (hourPair != null && hourPair.getLast() != null) {
                pair.setHourChanged(getPriceChange(hourPair.getLast(), last));
                pair.setHourChangedVolume(getVolumeChange(hourPair.getVolume(), volume));


            }
            if (threeHoursPair != null && threeHoursPair.getLast() != null) {
                pair.setThreeHourChanged(getPriceChange(threeHoursPair.getLast(), last));
                pair.setThreeHourChangedVolume(getVolumeChange(threeHoursPair.getVolume(), volume));


            }
            if (sixHoursPair != null && sixHoursPair.getLast() != null) {
                pair.setSixHourChanged(getPriceChange(sixHoursPair.getLast(), last));
                pair.setSixHourChangedVolume(getVolumeChange(sixHoursPair.getVolume(), volume));


            }
            if (tfHoursPair != null && tfHoursPair.getLast() != null) {
                pair.setTwentyHourChanged(getPriceChange(tfHoursPair.getLast(), last));
                pair.setTwentyHourChangedVolume(getVolumeChange(tfHoursPair.getVolume(), volume));
            }


            if (alltimePair != null && alltimePair.getLast() != null) {
                pair.setAllTimeChanged(getPriceChange(alltimePair.getLast(), last));
                pair.setAllTimeChangedVolume(getVolumeChange(alltimePair.getVolume(), volume));


            }
           // log.info("OK:set coefficents.. time {}", dateFormat.format(new Date()));

        }
      //  log.info("save pair.. time {}", dateFormat.format(new Date()));

        pairRepo.save(pair);
       // log.info("OK: save pair.. time {}", dateFormat.format(new Date()));



        return pair;
    }


    private Double getPriceChange(Double open, Double lastPrice) {
        Double newValue = null;

        try {
            newValue = ((lastPrice - open) / open) * 100;
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


    private Double getVolumeChange(Double volume, Double lastVolume) {
        Double newValue = null;

        try {
            newValue = ((lastVolume - volume) / volume) * 100;
            return truncatedDouble(newValue);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return null;


    }


}
