package ru.denisa.cryptopia;

import com.cf.client.poloniex.PoloniexExchangeService;
import com.cf.data.model.poloniex.PoloniexTicker;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.cryptopia.CryptopiaExchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * Created by d.aleksandrov on 14.01.2018.
 */
@Slf4j
@Component
public class CryptopiaDataService implements InitializingBean {

    private static MarketDataService marketDataService;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    LoadingCache<String, LinkedList<Ticker>> minuteCache;





    //обновляется каждую минуту
    public LinkedList<Ticker> getArbitrageLastTickers() {
        if (minuteCache == null) {

            minuteCache = Caffeine.newBuilder()
                    .maximumSize(10)
                    .refreshAfterWrite(60000, TimeUnit.MILLISECONDS)
                    .build(ticker -> { // Using a jOOQ repository

                        return getArbitrageLastTickersDao();
                    });
        }
        return minuteCache.get("");
    }






    private LinkedList<Ticker> getArbitrageLastTickersDao() {

        LinkedList<Ticker> tickers = new LinkedList();

        try {
            Ticker eth = marketDataService.getTicker(CurrencyPair.ETH_BTC);
            Ticker doge = marketDataService.getTicker(CurrencyPair.DOGE_BTC);
            Ticker ltc = marketDataService.getTicker(CurrencyPair.LTC_BTC);
            Ticker dash = marketDataService.getTicker(CurrencyPair.DASH_BTC);
            Ticker etc = marketDataService.getTicker(CurrencyPair.ETC_BTC);
          //  Ticker xrp = marketDataService.getTicker(CurrencyPair.XRP_BTC);

            tickers.add(eth);
            tickers.add(doge);
            tickers.add(ltc);
            tickers.add(dash);
            tickers.add(etc);



        } catch (IOException e) {
            log.error(e.getMessage(), dateFormat.format(new Date()));
        }

        return tickers;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(CryptopiaExchange.class.getName());
        marketDataService = exchange.getMarketDataService();
    }


}
