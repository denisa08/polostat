package ru.denisa.bittrex.model;


import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * Created by d.aleksandrov on 12.11.2017.
 */
public enum Role implements GrantedAuthority {

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");
    private String authority;

    Role(String authority) {
        this.authority = authority;
    }


    @Override
    public String getAuthority() {
        return this.authority;
    }


}
