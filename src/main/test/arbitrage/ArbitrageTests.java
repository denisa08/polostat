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
            if (arbitragePairs!=null&&arbitragePairs.size()>0){
                arbitragePairs.forEach(arbitragePair -> {
                    System.out.println(arbitragePair);

                });
            }

            Thread.sleep(30000);

        }




    }

}
