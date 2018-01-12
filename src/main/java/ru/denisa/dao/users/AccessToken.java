package ru.denisa.dao.users;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.denisa.model.user.AppUser;

import java.util.Date;
import java.util.List;

/**
 * Created by d.aleksandrov on 12.11.2017.
 */
@Document(collection = "AccessToken")
public class AccessToken {
    @Id
    private Long id;
    private String token;
    private Date expiry;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public boolean isExpired()
    {
        if (null == this.expiry) {
            return false;
        }

        return this.expiry.getTime() > System.currentTimeMillis();
    }


    public List<AppUser> getUser() {
        return user;
    }

    @DBRef
    private List<AppUser> user;


}
