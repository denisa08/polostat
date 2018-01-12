package ru.denisa.service.telegram.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.denisa.service.telegram.model.Chat;
import ru.denisa.service.telegram.dao.db.TelegramSelect;

import java.util.List;

/**
 * Created by d.aleksandrov on 02.11.2017.
 */
@Component
public class TelegramDao {

    @Autowired
    private TelegramSelect telegramSelect;


    public List<Chat> findAllTelegramChats() {
        return telegramSelect.findAllTelegramChats();
    }

    public void save(Chat chat) {
        telegramSelect.save(chat);
    }

    public Chat findChat(Long id) {
        return telegramSelect.findChat(id);
    }


}
