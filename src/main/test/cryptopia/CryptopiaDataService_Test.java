package cryptopia;

import io.jsonwebtoken.lang.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;
import ru.denisa.TestBoot;
import ru.denisa.cryptopia.CryptopiaDataService;

import java.util.LinkedList;

/**
 * Created by d.aleksandrov on 14.01.2018.
 */

@Profile("dev")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestBoot.class})
public class CryptopiaDataService_Test {


    @Autowired
    CryptopiaDataService cryptopiaDataService;

    @Test
    public void test_cryptopiaDataService_GetArbPairs(){
        LinkedList<Ticker> tickers = cryptopiaDataService.getArbitrageLastTickers();
        Assert.notNull(tickers);
        Assert.isTrue(tickers.size()>0);
        tickers.forEach(ticker -> {
            System.out.println(ticker);


        });


    }

}
