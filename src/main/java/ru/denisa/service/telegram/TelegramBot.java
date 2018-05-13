package ru.denisa.service.telegram;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import ru.denisa.service.telegram.model.Chat;
import ru.denisa.service.telegram.dao.TelegramDao;

/**
 * Created by d.aleksandrov on 06.11.2017.
 */
public class TelegramBot extends TelegramLongPollingBot {

    private String botUserName;

    private String botToken;
    private TelegramDao telegramDao;


    public TelegramBot(String botUserName, String botToken, TelegramDao telegramDao) {
        this.botUserName = botUserName;
        this.botToken = botToken;
        this.telegramDao = telegramDao;
    }


    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();


        if (message != null && message.hasText()) {


            if (telegramDao.findChat(message.getChatId()) != null) {


                if (message.getText().equals("/listnotify")) {

                    Chat chat = telegramDao.findChat(message.getChatId());

                    String query = new StringBuffer()
                            .append("/turn : " + (chat.isEnable() ? "on" : "off") + "\n")
                            .append("/sendfrom5pct : " + (chat.isSendfrom5pct() ? "on" : "off") + "\n")
                            .append("/sendfrom7pct : " + (chat.isSendfrom7pct() ? "on" : "off") + "\n")
                            .append("/sendfrom10pct : " + (chat.isSendfrom10pct() ? "on" : "off") + "\n")
                            .append("/sendfrom15pct : " + (chat.isSendfrom15pct() ? "on" : "off") + "\n")
                            .append("/sendfrom20pct : " + (chat.isSendfrom20pct() ? "on" : "off") + "\n")
                            .append("/sendfrom30pct : " + (chat.isSendfrom30pct() ? "on" : "off") + "\n")


                            .toString();

                    sendMsg(message.getChatId(), query);


                } else if (message.getText().equals("/sendfrom5pct")) {

                    Chat chat = telegramDao.findChat(message.getChatId());
                    if (!chat.isSendfrom5pct()) {
                        chat.setSendfrom5pct(true);
                        telegramDao.save(chat);
                        sendMsg(message.getChatId(), "OK, I will send you all changes volume from 5 pct. If you want turn off this notifications, please type /sendfrom5pct again");

                    } else {
                        chat.setSendfrom5pct(false);
                        telegramDao.save(chat);
                        sendMsg(message.getChatId(), "Send 5 ptc volume changes turned off. If you want turn on this notifications, please type /sendfrom5pct again.");


                    }
                } else if (message.getText().equals("/sendfrom7pct")) {

                    Chat chat = telegramDao.findChat(message.getChatId());
                    if (!chat.isSendfrom7pct()) {
                        chat.setSendfrom7pct(true);
                        telegramDao.save(chat);
                        sendMsg(message.getChatId(), "OK, I will send you all changes volume from 7 pct. If you want turn off this notifications, please type /sendfrom7pct again");

                    } else {
                        chat.setSendfrom7pct(false);
                        telegramDao.save(chat);
                        sendMsg(message.getChatId(), "Send 7 ptc volume changes turned off. If you want turn on this notifications, please type /sendfrom7pct again.");


                    }
                } else if (message.getText().equals("/sendfrom10pct")) {

                    Chat chat = telegramDao.findChat(message.getChatId());
                    if (!chat.isSendfrom10pct()) {
                        chat.setSendfrom10pct(true);
                        telegramDao.save(chat);
                        sendMsg(message.getChatId(), "OK, I will send you all changes volume from 10 pct. If you want turn off this notifications, please type /sendfrom10pct again");

                    } else {
                        chat.setSendfrom10pct(false);
                        telegramDao.save(chat);
                        sendMsg(message.getChatId(), "Send 10 ptc volume changes turned off. If you want turn on this notifications, please type /sendfrom10pct again.");


                    }
                } else if (message.getText().equals("/sendfrom15pct")) {

                    Chat chat = telegramDao.findChat(message.getChatId());
                    if (!chat.isSendfrom15pct()) {
                        chat.setSendfrom15pct(true);
                        telegramDao.save(chat);
                        sendMsg(message.getChatId(), "OK, I will send you all changes volume from 15 pct. If you want turn off this notifications, please type /sendfrom15pct again");

                    } else {
                        chat.setSendfrom15pct(false);
                        telegramDao.save(chat);
                        sendMsg(message.getChatId(), "Send 15 ptc volume changes turned off. If you want turn on this notifications, please type /sendfrom15pct again.");


                    }
                } else if (message.getText().equals("/sendfrom20pct")) {

                    Chat chat = telegramDao.findChat(message.getChatId());
                    if (!chat.isSendfrom20pct()) {
                        chat.setSendfrom20pct(true);
                        telegramDao.save(chat);
                        sendMsg(message.getChatId(), "OK, I will send you all changes volume from 20 pct. If you want turn off this notifications, please type /sendfrom20pct again");

                    } else {
                        chat.setSendfrom20pct(false);
                        telegramDao.save(chat);
                        sendMsg(message.getChatId(), "Send 20 ptc volume changes turned off. If you want turn on this notifications, please type /sendfrom20pct again.");


                    }
                } else if (message.getText().equals("/sendfrom30pct")) {

                    Chat chat = telegramDao.findChat(message.getChatId());
                    if (!chat.isSendfrom30pct()) {
                        chat.setSendfrom30pct(true);
                        telegramDao.save(chat);
                        sendMsg(message.getChatId(), "OK, I will send you all changes volume from 30 pct. If you want turn off this notifications, please type /sendfrom30pct again");

                    } else {
                        chat.setSendfrom30pct(false);
                        telegramDao.save(chat);
                        sendMsg(message.getChatId(), "Send 30 ptc volume changes turned off. If you want turn on this notifications, please type /sendfrom30pct again.");


                    }
                } else if (message.getText().equals("/turn")) {

                    Chat chat = telegramDao.findChat(message.getChatId());
                    if (!chat.isEnable()) {
                        chat.setEnable(true);
                        telegramDao.save(chat);
                        sendMsg(message.getChatId(), "I'll work for you my whole life!:) All notification turned on.\uD83D\uDE0C");

                    } else {
                        chat.setEnable(false);
                        telegramDao.save(chat);
                        sendMsg(message.getChatId(), "All notification turned off. I won't bother you anymore. \uD83D\uDE4A");


                    }
                } else {


                    String query = new StringBuffer()
                            .append("⭐ Welcome!" + "\n")
                            .append("My name is M6 Bot." + "\n")
                            .append("I'll notify you about all major changes in the bittrex.com. Just wait me:)." + "\n")
                            .append("Good Luck!" + "\n")
                            .append("⭐ ps: I'm from 194.87.96.39:8080/btx/# ⭐")


                            .toString();
                    sendMsg(message.getChatId(), query);
                }


            } else {

                Chat chat = new Chat();
                chat.setId(message.getChat().getId());
                chat.setEnable(true);
                chat.setSendfrom5pct(true);
                chat.setSendfrom7pct(true);
                chat.setSendfrom10pct(true);
                chat.setSendfrom15pct(true);
                chat.setSendfrom20pct(true);
                chat.setSendfrom30pct(true);


                telegramDao.save(chat);
                String query = new StringBuffer()
                        .append("Welcome!" + "\n")
                        .append("My name is M6 Bot." + "\n")
                        .append("I'll notify you about all major changes in the bittrex.com. Just wait me:)" + "\n")
                        .append("Good Luck!" + "\n")
                        .append("⭐ ps: I'm from 194.87.96.39:8080/btx/# ⭐")


                        .toString();
                sendMsg(message.getChatId(), query);
            }


        }
    }


    public void sendMsg(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);

        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
