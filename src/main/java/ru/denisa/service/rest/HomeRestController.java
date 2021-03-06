package ru.denisa.service.rest;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.denisa.dao.users.IUserDao;
import ru.denisa.model.user.AppUser;
import ru.denisa.model.user.Keys;
import ru.denisa.model.user.RequestUser;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

/**
 * Created by D.Aleksandrov on 18.11.2017.
 */
@RestController
public class HomeRestController {
    @Autowired
    private IUserDao appUserRepository;

    /**
     * This method is used for user registration. Note: user registration is not
     * require any authentication.
     *
     * @param appUser
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser appUser) {

        if (appUserRepository.loadUserByUsername(appUser.getUsername()) != null) {
            throw new RuntimeException("Username already exist");
        }

        List<String> roles = new ArrayList<>();
        roles.add("USER");
        roles.add("ADMIN");
        Keys keys = new Keys();
        keys.setApiKeyPolo("11111");
        keys.setApiKeyBtrx("11111");


        appUser.setKeys(keys);
        appUser.setRoles(roles);
        return new ResponseEntity<AppUser>(appUserRepository.save(appUser), HttpStatus.CREATED);
    }




    /**
     * This method is used for user registration. Note: user registration is not
     * require any authentication.
     *
     * @param appUser
     * @return
     */
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public ResponseEntity<AppUser> pay(@RequestBody AppUser appUser) {
   /*     AppUser user = appUserRepository.loadUserByUsername(appUser.getUsername());
        if (user == null) {
            throw new RuntimeException("Username doesnt exist");
        }
        user.getRoles().add("DENIS");*/

        return null;
    }



    /**
     * This method will return the logged user.
     *
     * @param principal
     * @return Principal java security principal object
     */
    @RequestMapping("/user")
    public AppUser user(Principal principal) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedUsername = auth.getName();
        return appUserRepository.loadUserByUsername(loggedUsername);
    }

    /**
     *
     * @param response
     * @return JSON contains token and user after success authentication.
     * @throws IOException
     */
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> login(@RequestBody RequestUser params,
                                                     HttpServletResponse response) throws IOException {
        String token = null;
        AppUser appUser = appUserRepository.loadUserByUsername(params.getUsername());


        Map<String, Object> tokenMap = new HashMap<String, Object>();
        if (appUser != null && appUser.getPassword().equals(params.getPassword())) {



            token = Jwts.builder().setSubject(params.getUsername()).claim("roles", appUser.getRoles()).setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
            tokenMap.put("token", token);
            tokenMap.put("user", appUser);
            return new ResponseEntity<Map<String, Object>>(tokenMap, HttpStatus.OK);
        } else {
            tokenMap.put("token", null);
            return new ResponseEntity<Map<String, Object>>(tokenMap, HttpStatus.UNAUTHORIZED);
        }

    }
}