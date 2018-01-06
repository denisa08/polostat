package ru.denisa.telegram.observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.denisa.model.Chat;
import ru.denisa.model.Pair;
import ru.denisa.telegram.TelegramFacade;
import ru.denisa.telegram.dao.TelegramDao;

import java.util.*;

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

    private Map<String,Pair> pairVolumeSet = new HashMap<>();
    private Map<String,Pair> pairPriceSet = new HashMap<>();






    @Scheduled(fixedRate = 300000)
    @Override
    public void handle5minChangePrice() {
        List<Chat> chatList = telegramDao.findAllTelegramChats();
        chatList.forEach(chat ->  pairPriceSet.forEach((s, pair) ->telegramBot.sendMessage(chat.getId(), createMessageForPriceChange(pair))));
        //удаляем множество для следующей партии
        pairPriceSet.clear();
    }

    //run every 5 minutes
    @Scheduled(fixedRate = 300000)
    @Override
    public void handle5minChangeVolume() {
        List<Chat> chatList = telegramDao.findAllTelegramChats();


        chatList.forEach(chat ->  pairVolumeSet.forEach((s, pair) ->telegramBot.sendMessage(chat.getId(), createMessageForVolumeChange(pair))));
        //удаляем множество для следующей партии
        pairVolumeSet.clear();

    }

    @Override
    public void handleVolumeChange(Pair pair) {
        pairVolumeSet.put(pair.getName(),pair);
    }

    @Override
    public void handlePriceChange(Pair pair) {
        pairPriceSet.put(pair.getName(),pair);

    }




    private String createMessageForVolumeChange(Pair pair) {
        String query;

        query = new StringBuffer()
                .append("Volume of " + pair.getName() + "" + " ")
                .append("is going up in the last 10 minutes. Check it.\n")
                .append("10 min change: " + pair.getTenChangedVolume() + " %\n")
                .append("30 min change: " + pair.getThirtyChangedVolume() + " %\n")
                .append("1 hour change: " + pair.getHourChangedVolume() + " %\n")
                .append("3 hour change: " + pair.getThreeHourChangedVolume() + " %\n")
                .append("Check it in https://bittrex.com/Market/Index?MarketName=" + pair.getName() + "\n")
                .append("⭐ More info: " + m6Site + " ⭐")
                .toString();


        return query;
    }


    private String createMessageForPriceChange(Pair pair) {
        String query;

        query = new StringBuffer()
                .append("Price of " + pair.getName() + "" + " ")
                .append("is going up in the last 10 minutes. Check it.\n")
                .append("Price: " + pair.getLast() + " btc\n")
                .append("10 min change: " + pair.getTenChanged() + " %\n")
                .append("30 min change: " + pair.getThirtyChanged() + " %\n")
                .append("1 hour change: " + pair.getHourChanged() + " %\n")
                .append("3 hour change: " + pair.getThreeHourChanged() + " %\n")
                .append("High price: " + pair.getHigh() + " btc\n")
                .append("Low price: " + pair.getLow() + " btc\n")
                .append("Check it in https://bittrex.com/Market/Index?MarketName=" + pair.getName() + "\n")
                .append("⭐ More info: " + m6Site + " ⭐")
                .toString();


        return query;
    }





}
