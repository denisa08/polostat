package ru.denisa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.denisa.dao.db.DBAuthorizationSelect;
import ru.denisa.model.AppUser;

/**
 * Created by d.aleksandrov on 12.11.2017.
 */
@Component
public class UserDao implements IUserDao {

    @Autowired
    private DBAuthorizationSelect dbAuthorizationSelect;

    @Override
    public AppUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return dbAuthorizationSelect.loadUserByUsername(username);
    }

    @Override
    public AppUser findByName(String name) {
        return dbAuthorizationSelect.findByName(name);
    }

    @Override
    public AppUser loadUserByName(String name) throws UsernameNotFoundException {
        return dbAuthorizationSelect.loadUserByName(name);
    }

    @Override
    public AppUser save(AppUser user) {
       return  dbAuthorizationSelect.save(user);

    }
}
