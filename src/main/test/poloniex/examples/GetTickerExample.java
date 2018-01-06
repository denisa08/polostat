package poloniex.examples;

import com.cf.client.poloniex.PoloniexExchangeService;
import com.cf.data.model.poloniex.PoloniexTicker;

import java.util.List;

/**
 * Created by d.aleksandrov on 03.01.2018.
 */
public class GetTickerExample {

    public static void main (String str[]){

        String apiKey = "N9H3TERJ-FAYLL15B-EPFBUATS-A97S0QHE";
        String apiSecret = "227e47cc5e4a98f890ac8680fd5207e7eae4f32d5ee7c1ccd14b191424d34ca5109d1df78632a6a39ab8aead87967501e64f25502f7e5fc4d11aa4f7949969a6";
        PoloniexExchangeService service = new PoloniexExchangeService(apiKey, apiSecret);
        PoloniexTicker btcTicker = service.returnTicker("BTC_ETH");
        System.out.println(btcTicker);

        List<String> marketsList = service.returnAllMarkets();

        System.out.println(marketsList);


    }


}
