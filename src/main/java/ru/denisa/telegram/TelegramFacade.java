package ru.denisa.telegram;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import ru.denisa.telegram.dao.TelegramDao;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by d.aleksandrov on 26.10.2017.
 */

@Profile({"prod"})
@Component
@PropertySource(value = {"classpath:application-prod.properties"})

public class TelegramFacade implements InitializingBean {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(TelegramFacade.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private TelegramDao telegramDao;


    @Value("${spring.botusername}")
    private String botUserName;

    @Value("${spring.bottoken}")
    private String botToken;

    private TelegramBot telegramBot;


    private static void initBot(TelegramFacade telegramFacade) {
       ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        TelegramBot telegramBot = new TelegramBot(telegramFacade.botUserName, telegramFacade.botToken, telegramFacade.telegramDao);
        telegramFacade.telegramBot = telegramBot;

        try {
            telegramBotsApi.registerBot(telegramBot);
        } catch (Exception ex) {
            log.error(dateFormat.format(new Date()) + "Telegram bot errror! " + ex.getMessage());
        }
    }

    public void sendMessage(Long chatId, String message) {
        try{
            telegramBot.sendMsg(chatId, message);
        }
        catch (Exception ex){

        }

    }


    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("init: going to init Telegram bot...");
        TelegramFacade.initBot(this);
        log.info("init: Telegram bot is inited:)");

    }
}
