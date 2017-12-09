package ru.denisa.dao;

import ru.denisa.model.AccessToken;

/**
 * Created by d.aleksandrov on 12.11.2017.
 */
public interface IAccessTokenDao {
    public AccessToken findByToken(String accessTokenString);

    public void delete(String token);



}
