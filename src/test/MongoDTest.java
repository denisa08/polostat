import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.denisa.SpringBootApp;
import ru.denisa.TestBoot;
import ru.denisa.dao.db.DBSelect;
import ru.denisa.model.Pair;

import static org.glassfish.grizzly.http.util.Header.Date;

/**
 * Created by d.aleksandrov on 06.12.2017.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes={TestBoot.class})
public class MongoDTest {

    @Autowired
    DBSelect dbSelect;

    @Test
    public void loadMongoTest(){

        for (int i=955152;i<1000000000;i++){
            Pair pair = new Pair();
            pair.setId(new Long(i));
            pair.setName("test"+i);
            pair.setDate(new java.util.Date().getTime());
            dbSelect.save(pair);
             log.info("info--saved: "+pair.getId());


        }

    }


}
