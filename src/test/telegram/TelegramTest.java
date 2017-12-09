package telegram;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import ru.denisa.SpringBootApp;
import ru.denisa.telegram.TelegramFacade;

/**
 * Created by d.aleksandrov on 03.11.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={SpringBootApp.class})
public class TelegramTest {

    @Autowired
    private TelegramFacade telegramBot;




    @Test
    public void test1(){
         telegramBot.sendMessage(null,null);
    }



}
