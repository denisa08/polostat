package ru.denisa.dao.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.denisa.dao.db.DBAuthorizationSelect;

/**
 * Created by d.aleksandrov on 12.11.2017.
 */

@Repository
public class AccessTokenDao implements IAccessTokenDao {

    @Autowired
    private DBAuthorizationSelect dbAuthorizationSelect;


    @Override
    public AccessToken findByToken(String accessTokenString) {
        return dbAuthorizationSelect.findByToken(accessTokenString);
    }

    @Override
    public void delete(String token) {
        dbAuthorizationSelect.deleteToken(token);
    }
}
