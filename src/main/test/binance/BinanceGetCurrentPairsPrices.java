package binance;

import com.github.benmanes.caffeine.cache.Ticker;
import org.junit.BeforeClass;
import org.junit.Test;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.binance.BinanceExchange;
import org.knowm.xchange.binance.dto.marketdata.BinanceSymbolPrice;
import org.knowm.xchange.binance.dto.marketdata.BinanceTicker24h;
import org.knowm.xchange.binance.service.BinanceMarketDataService;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by d.aleksandrov on 11.01.2018.
 */
public class BinanceGetCurrentPairsPrices {

    static Exchange exchange;
    static MarketDataService marketService;

    @BeforeClass
    public static void beforeClass() {
        exchange = ExchangeFactory.INSTANCE.createExchange(BinanceExchange.class.getName());
        marketService = exchange.getMarketDataService();
    }




    @Test
    public void testCandleSticks() throws Exception {

        List<org.knowm.xchange.dto.marketdata.Ticker> tickers = new ArrayList<>();



        for (CurrencyPair cp : exchange.getExchangeMetaData().getCurrencyPairs().keySet()) {
            if (cp.counter == Currency.BTC) {
                tickers.add(getBinanceTicker24h(cp));
            }
        }

        tickers.stream().forEach(ticker -> {

            System.out.println("ticker name:  last: " + ticker.getLast()+" name"+ticker.getCurrencyPair().base.getDisplayName());
            System.out.println("");


        });

    }

    private org.knowm.xchange.dto.marketdata.Ticker getBinanceTicker24h(CurrencyPair pair) throws IOException {
        BinanceMarketDataService service = (BinanceMarketDataService) marketService;
        return service.getTicker(pair);
    }


}
