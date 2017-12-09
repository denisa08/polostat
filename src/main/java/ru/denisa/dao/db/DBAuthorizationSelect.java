package ru.denisa.dao.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.denisa.dao.SequenceDAO;
import ru.denisa.model.AccessToken;
import ru.denisa.model.AppUser;

/**
 * Created by d.aleksandrov on 12.11.2017.
 */
@Component
public class DBAuthorizationSelect {
    @Autowired
    private SequenceDAO sequenceDAO;

    @Autowired
    private MongoOperations mongoOperations;

    public AccessToken findByToken(String accessTokenString) {

        Query query = new Query();
        query.addCriteria(Criteria.where("token").is(accessTokenString));
        return mongoOperations.findOne(query, AccessToken.class);
    }


    public AppUser loadUserByUsername(String username) throws UsernameNotFoundException {

        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(username));
        return mongoOperations.findOne(query, AppUser.class);

    }

    public AppUser findByName(String name) {
        return loadUserByUsername(name);

    }


    public void deleteToken(String token) {
        Query query = new Query();
        query.addCriteria(Criteria.where("token").is(token));
        mongoOperations.findAndRemove(query, AccessToken.class);
    }


    public AppUser loadUserByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        return mongoOperations.findOne(query, AppUser.class);
    }

    public AppUser save(AppUser user) {
        user.setId(sequenceDAO.getNextSequenceId("pairs"));
        mongoOperations.save(user);
        return loadUserByName(user.getName());

    }
}
