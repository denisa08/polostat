package ru.denisa.dao.users;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.denisa.model.user.AppUser;

/**
 * Created by d.aleksandrov on 12.11.2017.
 */
public interface IUserDao {
    AppUser loadUserByUsername(String username) throws UsernameNotFoundException;

    AppUser findByName(String name);


    AppUser loadUserByName(String email) throws UsernameNotFoundException;


      AppUser save (AppUser user);
}
