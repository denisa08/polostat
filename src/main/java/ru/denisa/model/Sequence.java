package ru.denisa.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by root on 13/07/17.
 */

@Document(collection = Sequence.COLLECTION_NAME)
public class Sequence {

    public static final String COLLECTION_NAME="Sequences";

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getSequence() {
        return sequence;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    private Long sequence;











}
