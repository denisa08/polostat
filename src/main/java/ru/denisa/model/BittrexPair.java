package ru.denisa.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by root on 07/08/17.
 */
@Document(collection = "BittrexPair")
public class BittrexPair {

    @Id
    @Indexed
    private Long id;
    @Indexed
    private String name;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
