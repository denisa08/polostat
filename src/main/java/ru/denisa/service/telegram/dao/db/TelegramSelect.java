package ru.denisa.service.telegram.dao.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ru.denisa.service.telegram.model.Chat;

import java.util.List;

/**
 * Created by d.aleksandrov on 02.11.2017.
 */

@Repository
public class TelegramSelect {
    @Autowired
    private MongoOperations mongoOperations;

    public void save(Chat chat) {
        mongoOperations.save(chat);
    }

    public List<Chat> findAllTelegramChats() {
        return mongoOperations.findAll(Chat.class);
    }


    public Chat findChat(Long id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoOperations.findOne(query, Chat.class);
    }


}
