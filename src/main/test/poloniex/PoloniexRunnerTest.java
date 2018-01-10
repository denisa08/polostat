package poloniex;

import com.cf.data.model.poloniex.PoloniexTicker;
import io.jsonwebtoken.lang.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;
import ru.denisa.TestBoot;
import ru.denisa.dao.PairDAO;
import ru.denisa.model.Pair;

import java.util.ArrayList;

/**
 * Created by d.aleksandrov on 03.01.2018.
 */
@Profile("dev")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestBoot.class})
public class PoloniexRunnerTest {


    @Autowired
    private PairDAO pairDAO;



    @Test
    @Repeat(value = 5)
    @Ignore
    public void get_bittrex_polo_diff() throws InterruptedException {
/*
        ArrayList<PoloniexTicker> poloniexTickers = poloniexRunner.getTickers();
        poloniexTickers.forEach(poloniexTicker -> {
            Assert.notNull(poloniexTicker.getName());
            System.out.println("Name = " + poloniexTicker.getName());
            Assert.notNull(poloniexTicker.getLast());
            System.out.println("Last = " + poloniexTicker.getLast());
            Assert.notNull(poloniexTicker.getBaseVolume());
            System.out.println("BaseVolume = " + poloniexTicker.getBaseVolume());
            Assert.notNull(poloniexTicker.getHighestBid());
            System.out.println("HighestBid = " + poloniexTicker.getHighestBid());
            Assert.notNull(poloniexTicker.getLast());
            System.out.println("Last = " + poloniexTicker.getLast());
            Assert.notNull(poloniexTicker.getQuoteVolume());
            System.out.println("QuoteVolume = " + poloniexTicker.getQuoteVolume());
            Assert.notNull(poloniexTicker.getLowestAsk());
            System.out.println("LowestAsk = " + poloniexTicker.getLowestAsk());
            Assert.notNull(poloniexTicker.getPercentChange());
            System.out.println("PercentChange = " + poloniexTicker.getPercentChange());
      });
*/



    }





}
