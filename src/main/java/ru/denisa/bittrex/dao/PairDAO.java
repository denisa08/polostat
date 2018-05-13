package ru.denisa.bittrex.dao;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;
import ru.denisa.bittrex.dao.db.DBSelect;
import ru.denisa.service.cache.CrunchifyInMemoryCache;
import ru.denisa.bittrex.model.BittrexPair;
import ru.denisa.bittrex.model.Pair;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by root on 13/07/17.
 */

@Repository
public class PairDAO implements InitializingBean {
    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private DBSelect dbSelect;

    public HashMap<String, CrunchifyInMemoryCache> cacheMap = new HashMap<String, CrunchifyInMemoryCache>();


    LoadingCache<String, Pair> oneMinuteCache;
    LoadingCache<String, Pair> fourMinuteCache;

    LoadingCache<String, Pair> twoMinuteCache;
    LoadingCache<String, Pair> threeMinuteCache;
    LoadingCache<String, Pair> fiveMinuteCache;
    LoadingCache<String, Pair> tenMinuteCache;

    LoadingCache<String, Pair> thirtyMinuteCache;
    private LoadingCache<String, Pair> hourCache;
    private LoadingCache<String, Pair> threeHourCache;
    private LoadingCache<String, Pair> sixHourCache;
    private LoadingCache<String, Pair> twelveHourCache;
    private LoadingCache<String, Pair> twentyFourCache;
    private LoadingCache<String, Pair> weekCache;


    @Value("${pairsBTX}")
    private String btxPairProp;
    private String[] btxStrPairs;


    LoadingCache<String, List<BittrexPair>> pairsCache = null;


    private static final org.slf4j.Logger log = LoggerFactory.getLogger(PairDAO.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private final int cacheSize = 1000;


    public void save(Pair pair) {
        dbSelect.save(pair);
    }

    public void save(BittrexPair pair) {
        dbSelect.save(pair);
    }


    public boolean checkBitrexPairs() {
        return dbSelect.checkBitrexPairs();
    }


    public List<BittrexPair> getBittrexPairs() {

        if (pairsCache == null) {

            pairsCache = Caffeine.newBuilder()
                    .maximumSize(1000)
                    .refreshAfterWrite(24, TimeUnit.HOURS)
                    .build(title -> { // Using a jOOQ repository


                        return dbSelect.findAllBtxPairs();
                    });
        }

        return pairsCache.get("");
    }


    public ArrayList<Pair> getLastArbitragePairs() {

        ArrayList<Pair> pairs = new ArrayList();

        Arrays.stream(btxStrPairs).forEach(s -> {

            Pair pair = getLastPair(s);
            if (pair != null) {
                pairs.add(pair);
            }


        });
        return pairs;
    }

    public Pair getLastPair(String pairName) {
        if (oneMinuteCache == null) {

            oneMinuteCache = Caffeine.newBuilder()
                    .maximumSize(1000)
                    .refreshAfterWrite(1, TimeUnit.MINUTES)
                    .build(name -> { // Using a jOOQ repository


                        return dbSelect.getLastPairDB(name);
                    });
        }
        return oneMinuteCache.get(pairName);
    }


    public Pair getFirstPair5Minutes(String pairName) {
        if (fiveMinuteCache == null) {

            fiveMinuteCache = Caffeine.newBuilder()
                    .maximumSize(1000)
                    .refreshAfterWrite(5, TimeUnit.MINUTES)
                    .build(name -> { // Using a jOOQ repository


                        return dbSelect.getFirstPair5MinutesDB(name);
                    });
        }
        return fiveMinuteCache.get(pairName);
    }


    public Pair getFirstPair4Minutes(String pairName) {
        if (fourMinuteCache == null) {

            fourMinuteCache = Caffeine.newBuilder()
                    .maximumSize(1000)
                    .refreshAfterWrite(162000, TimeUnit.MILLISECONDS)
                    .build(name -> { // Using a jOOQ repository


                        return dbSelect.getFirstPair4MinutesDB(name);
                    });
        }
        return fourMinuteCache.get(pairName);
    }

    public Pair getFirstPair3Minutes(String pairName) {
        if (threeMinuteCache == null) {

            threeMinuteCache = Caffeine.newBuilder()
                    .maximumSize(1000)
                    .refreshAfterWrite(3, TimeUnit.MINUTES)
                    .build(name -> { // Using a jOOQ repository


                        return dbSelect.getFirstPair3MinutesDB(name);
                    });
        }
        return threeMinuteCache.get(pairName);
    }


    public Pair getFirstPair2Minutes(String pairName) {
        if (twoMinuteCache == null) {

            twoMinuteCache = Caffeine.newBuilder()
                    .maximumSize(1000)
                    .refreshAfterWrite(2, TimeUnit.MINUTES)
                    .build(name -> { // Using a jOOQ repository


                        return dbSelect.getFirstPair2MinutesDB(name);
                    });
        }
        return twoMinuteCache.get(pairName);
    }


    public Pair getFirstPair10Minutes(String pairName) {
        if (tenMinuteCache == null) {

            tenMinuteCache = Caffeine.newBuilder()
                    .maximumSize(1000)
                    .refreshAfterWrite(10, TimeUnit.MINUTES)
                    .build(name -> { // Using a jOOQ repository


                        return dbSelect.getFirstPair10MinutesDB(name);
                    });
        }
        return tenMinuteCache.get(pairName);
    }


    public Pair getFirstPair30Minutes(String pairName) {
        if (thirtyMinuteCache == null) {

            thirtyMinuteCache = Caffeine.newBuilder()
                    .maximumSize(1000)
                    .refreshAfterWrite(30, TimeUnit.MINUTES)
                    .build(name -> { // Using a jOOQ repository


                        return dbSelect.getFirstPair30MinutesDB(name);
                    });
        }
        return thirtyMinuteCache.get(pairName);
    }


    public Pair getFirstPair1Hour(String pairName) {

        if (hourCache == null) {

            hourCache = Caffeine.newBuilder()
                    .maximumSize(1000)
                    .refreshAfterWrite(1, TimeUnit.HOURS)
                    .build(name -> { // Using a jOOQ repository


                        return dbSelect.getFirstPair1HourDB(name);
                    });
        }
        return hourCache.get(pairName);
    }


    public Pair getFirstPair3Hours(String pairName) {
        if (threeHourCache == null) {

            threeHourCache = Caffeine.newBuilder()
                    .maximumSize(1000)
                    .refreshAfterWrite(3, TimeUnit.HOURS)
                    .build(name -> { // Using a jOOQ repository


                        return dbSelect.getFirstPair3HoursDB(name);
                    });
        }
        return threeHourCache.get(pairName);
    }


    public Pair getFirstPair6Hours(String pairName) {
        if (sixHourCache == null) {

            sixHourCache = Caffeine.newBuilder()
                    .maximumSize(1000)
                    .refreshAfterWrite(6, TimeUnit.HOURS)
                    .build(name -> { // Using a jOOQ repository


                        return dbSelect.getFirstPair6HoursDB(name);
                    });
        }
        return sixHourCache.get(pairName);
    }


    public Pair getFirstPair12Hours(String pairName) {
        if (twelveHourCache == null) {

            twelveHourCache = Caffeine.newBuilder()
                    .maximumSize(1000)
                    .refreshAfterWrite(12, TimeUnit.HOURS)
                    .build(name -> { // Using a jOOQ repository


                        return dbSelect.getFirstPair12HoursDB(name);
                    });
        }
        return twelveHourCache.get(pairName);
    }

    public Pair getFirstPair24Hours(String pairName) {
        if (twelveHourCache == null) {

            twelveHourCache = Caffeine.newBuilder()
                    .maximumSize(1000)
                    .refreshAfterWrite(24, TimeUnit.HOURS)
                    .build(name -> { // Using a jOOQ repository


                        return dbSelect.getFirstPair24HoursDB(name);
                    });
        }
        return twelveHourCache.get(pairName);
    }


    public Pair getFirstPair3Day(String pairName) {
        if (weekCache == null) {

            weekCache = Caffeine.newBuilder()
                    .maximumSize(1000)
                    .refreshAfterWrite(3, TimeUnit.DAYS)
                    .build(name -> { // Using a jOOQ repository


                        return dbSelect.getFirstPair3DayDB(name);
                    });
        }
        return weekCache.get(pairName);

    }

    public Pair getFirstPair5Day(String pairName) {
        if (weekCache == null) {

            weekCache = Caffeine.newBuilder()
                    .maximumSize(1000)
                    .refreshAfterWrite(5, TimeUnit.DAYS)
                    .build(name -> { // Using a jOOQ repository


                        return dbSelect.getFirstPair5DayDB(name);
                    });
        }
        return weekCache.get(pairName);

    }


    public Pair getFirstPair7Day(String pairName) {
        if (weekCache == null) {

            weekCache = Caffeine.newBuilder()
                    .maximumSize(1000)
                    .refreshAfterWrite(7, TimeUnit.DAYS)
                    .build(name -> { // Using a jOOQ repository


                        return dbSelect.getFirstPair7DayDB(name);
                    });
        }
        return weekCache.get(pairName);

    }


    @Override
    public void afterPropertiesSet() throws Exception {
        btxStrPairs = btxPairProp.toString().split("#");

    }


}
