package ru.denisa.telegram.observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ru.denisa.model.Chat;
import ru.denisa.model.Pair;
import ru.denisa.telegram.TelegramFacade;
import ru.denisa.telegram.dao.TelegramDao;

import java.util.List;

/**
 * Created by d.aleksandrov on 02.11.2017.
 */
@Profile({"prod"})
@PropertySource(value = {"classpath:application-prod.properties"})

@Component
public class TelegramObserver implements ITelegramObserver {
    @Autowired
    private TelegramDao telegramDao;

    @Autowired
    private TelegramFacade telegramBot;

    @Value("${spring.m6site}")
    private String m6Site;


    @Override
    public void handle1minChange(Pair pair) {
        List<Chat> chatList = telegramDao.findAllTelegramChats();
        for (Chat chat : chatList) {
            if (canSendMessageToChat(chat, pair)) {
                telegramBot.sendMessage(chat.getId(), createMessageFor1MinChange(pair));

            }
        }
    }

    @Override
    public void handle5minChange(Pair pair) {

    }


    private boolean canSendMessageToChat(Chat chat, Pair pair) {
        double val = pair.getOneChangedVolume();
        if (val < 5) return false;
        if (!chat.isEnable()) return false;


        if (chat.isSendfrom5pct() && val >= 5) return true;
        if (chat.isSendfrom7pct() && val >= 7) return true;
        if (chat.isSendfrom10pct() && val >= 10) return true;
        if (chat.isSendfrom15pct() && val >= 15) return true;
        if (chat.isSendfrom20pct() && val >= 20) return true;
        if (chat.isSendfrom30pct() && val >= 30) return true;


        return false;
    }

    private String createMessageFor1MinChange(Pair pair) {
        String query = null;


        if (pair.getOneChangedVolume() >= 5 && pair.getOneChangedVolume() < 7) {
            query = new StringBuffer()
                    .append("Volume of " + pair.getName() + "" + " ")
                    .append("increased by " + pair.getOneChangedVolume() + " %  in the last minute!" + "\n")
                    .append("Check it in https://bittrex.com/Market/Index?MarketName=" + pair.getName() + "\n")
                    .append("⭐ More info: " + m6Site + " ⭐")
                    .toString();
        }

        if (pair.getOneChangedVolume() >= 10 && pair.getOneChangedVolume() < 20) {
            query = new StringBuffer()
                    .append("\uD83D\uDCB8 Volume of " + pair.getName() + "" + " ")
                    .append("increased by " + pair.getOneChangedVolume() + " %  in the last minute!" + "\n")
                    .append("Check it in https://bittrex.com/Market/Index?MarketName=" + pair.getName() + "\n")
                    .append("⭐ More info: " + m6Site + " ⭐")
                    .toString();
        }


        if (pair.getOneChangedVolume() >= 20 && pair.getOneChangedVolume() < 50) {
            query = new StringBuffer()
                    .append("\uD83D\uDCB8 \uD83D\uDCB8 Volume of " + pair.getName() + "" + " ")
                    .append("increased by " + pair.getOneChangedVolume() + " %  in the last minute!" + "\n")
                    .append("Check it in https://bittrex.com/Market/Index?MarketName=" + pair.getName() + "\n")
                    .append("⭐ More info: " + m6Site + " ⭐")
                    .toString();
        }


        if (pair.getOneChangedVolume() >= 50) {
            query = new StringBuffer()
                    .append("\uD83D\uDCB8 \uD83D\uDCB8 \uD83D\uDCB8 Volume of " + pair.getName() + "" + " ")
                    .append("increased by " + pair.getOneChangedVolume() + " %  in the last minute!" + "\n")
                    .append("Check it in https://bittrex.com/Market/Index?MarketName=" + pair.getName() + "\n")
                    .append("⭐ More info: " + m6Site + " ⭐")
                    .toString();
        }


        return query;
    }


}
