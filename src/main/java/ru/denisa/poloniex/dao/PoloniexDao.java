package ru.denisa.poloniex.dao;

import com.cf.client.poloniex.PoloniexExchangeService;
import com.cf.data.model.poloniex.PoloniexTicker;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ru.denisa.model.Pair;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by d.aleksandrov on 04.01.2018.
 */
@Slf4j
@PropertySource(value = {"classpath:application-dev.properties"})
@Component
public class PoloniexDao implements InitializingBean {

    LoadingCache<String, PoloniexTicker> fiveMinuteCache;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //Хранит массив имен поддерживаемых пар между битрексом и полониксом
    private String[] poloniexPairs;
    @Value("${polopairs}")
    private String poloPairProp;

    @Value("${polo.apiKey}")
    private String apiKey;

    @Value("${polo.apiSecret}")
    private String apiSecret;


    //обновляется каждые 4,5 минуты
    public PoloniexTicker getLastTicker(String pairName) {
        if (fiveMinuteCache == null) {

            fiveMinuteCache = Caffeine.newBuilder()
                    .maximumSize(100)
                    .refreshAfterWrite(270000, TimeUnit.MILLISECONDS)
                    .build(ticker -> { // Using a jOOQ repository
                        log.info("update PoloniexDao cache,attempt to add new ticker = "+ticker, dateFormat.format(new Date()));
                        PoloniexExchangeService service = new PoloniexExchangeService(apiKey, apiSecret);
                        PoloniexTicker tickerPolo = service.returnTicker(ticker);
                        log.info("update PoloniexDao cache, added new ticker = "+ticker, dateFormat.format(new Date()));
                         return tickerPolo;
                    });
        }
        return fiveMinuteCache.get(pairName);
    }


    public ArrayList<PoloniexTicker> getTickers() {
        ArrayList<PoloniexTicker> poloniexTickers = new ArrayList<>();
        Arrays.stream(poloniexPairs).forEach(ticker -> poloniexTickers.add(getLastTicker(ticker)));
        return poloniexTickers;
    }




    @Override
    public void afterPropertiesSet() throws Exception {
        poloniexPairs = poloPairProp.toString().split("#");

    }
}
