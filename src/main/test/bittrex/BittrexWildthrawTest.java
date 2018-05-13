package bittrex;

import de.elbatya.cryptocoins.bittrexclient.BittrexClient;
import de.elbatya.cryptocoins.bittrexclient.config.ApiCredentials;
import io.jsonwebtoken.lang.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;
import ru.denisa.TestBoot;
import ru.denisa.bittrex.sys.Bittrex;
import ru.denisa.dao.users.UserDao;
import ru.denisa.model.user.Keys;

/**
 * Created by d.aleksandrov on 15.01.2018.
 */

@Profile("dev")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestBoot.class})
public class BittrexWildthrawTest {

    @Autowired
    UserDao userDao;


    //Узнать баланс на кошельке

    @Test
    public void test1() {

        Keys keys = userDao.findByName("denis").getKeys();


        Assert.notNull(keys);
        String btxApiSecret = keys.getApiSercetBtrx();
        String btxApiKey = keys.getApiKeyBtrx();
        Assert.notNull(btxApiSecret);
        Assert.notNull(btxApiKey);


        ApiCredentials apiCredentials = new ApiCredentials();
        apiCredentials.setSecret(btxApiSecret);
        apiCredentials.setKey(btxApiKey);
        BittrexClient bittrexClient = new BittrexClient(apiCredentials);

        Assert.notNull(bittrexClient.getAccountApi().getBalance("BAT"));


    }

    @Test
    public void test2() {

        Keys keys = userDao.findByName("denis").getKeys();


        Assert.notNull(keys);
        String btxApiSecret = keys.getApiSercetBtrx();
        String btxApiKey = keys.getApiKeyBtrx();
        Assert.notNull(btxApiSecret);
        Assert.notNull(btxApiKey);


        ApiCredentials apiCredentials = new ApiCredentials();
        apiCredentials.setSecret(btxApiSecret);
        apiCredentials.setKey(btxApiKey);
        BittrexClient bittrexClient = new BittrexClient(apiCredentials);



    }


}
