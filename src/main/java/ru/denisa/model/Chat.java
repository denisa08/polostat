package ru.denisa.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by D.Aleksandrov on 30.10.2017.
 */
@Document(collection = "Chat")
public class Chat extends org.telegram.telegrambots.api.objects.Chat {
    @Id
    @Indexed
    private Long id;

    private boolean enable;
    private boolean sendfrom5pct;
    private boolean sendfrom7pct;
    private boolean sendfrom10pct;
    private boolean sendfrom15pct;
    private boolean sendfrom20pct;
    private boolean sendfrom30pct;

    String name;



    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isSendfrom5pct() {
        return sendfrom5pct;
    }

    public void setSendfrom5pct(boolean sendfrom5pct) {
        this.sendfrom5pct = sendfrom5pct;
    }

    public boolean isSendfrom7pct() {
        return sendfrom7pct;
    }

    public void setSendfrom7pct(boolean sendfrom7pct) {
        this.sendfrom7pct = sendfrom7pct;
    }

    public boolean isSendfrom10pct() {
        return sendfrom10pct;
    }

    public void setSendfrom10pct(boolean sendfrom10pct) {
        this.sendfrom10pct = sendfrom10pct;
    }

    public boolean isSendfrom15pct() {
        return sendfrom15pct;
    }

    public void setSendfrom15pct(boolean sendfrom15pct) {
        this.sendfrom15pct = sendfrom15pct;
    }

    public boolean isSendfrom20pct() {
        return sendfrom20pct;
    }

    public void setSendfrom20pct(boolean sendfrom20pct) {
        this.sendfrom20pct = sendfrom20pct;
    }

    public boolean isSendfrom30pct() {
        return sendfrom30pct;
    }

    public void setSendfrom30pct(boolean sendfrom30pct) {
        this.sendfrom30pct = sendfrom30pct;
    }









    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }





}
