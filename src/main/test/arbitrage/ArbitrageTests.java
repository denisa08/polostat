package arbitrage;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;
import ru.denisa.TestBoot;
import ru.denisa.arbitrage.service.ArbitrageService;
import ru.denisa.arbitrage.model.ArbitragePair;

import java.util.ArrayList;

/**
 * Created by d.aleksandrov on 09.01.2018.
 */
@Profile("dev")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestBoot.class})
public class ArbitrageTests {

    @Autowired
    ArbitrageService arbitrageService;


    @Test
     public void get_pairs_test1() throws InterruptedException {


      for (int i=0;i<3;i++){

            ArrayList<ArbitragePair> arbitragePairs =  arbitrageService.getPairs();
            arbitragePairs.forEach(arbitragePair -> {

                Assert.assertNotNull(arbitragePair.getName());
                System.out.println("getName"+arbitragePair.getName());


                Assert.assertNotNull(arbitragePair.getLastBtxPrice());
                System.out.println("LastPriceBTX"+arbitragePair.getLastBtxPrice());

                Assert.assertNotNull(arbitragePair.getLastBtxVolume());
                System.out.println("getLastBtxVolume"+arbitragePair.getLastBtxVolume());

                Assert.assertNotNull(arbitragePair.getLastPoloPrice());
                System.out.println("getLastPoloPrice"+arbitragePair.getLastPoloPrice());



                Assert.assertNotNull(arbitragePair.getPriceDiff());
                System.out.println("getPriceDiff"+arbitragePair.getPriceDiff());



            });
            Thread.sleep(60000);

        }




    }

}
