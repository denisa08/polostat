package ru.denisa.bittrex.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by D.Aleksandrov on 01/07/17.
 */
@Data
@Document(collection = "Pairs")

public class Pair {


    @Id
    @Indexed
    private Long id;

    @Indexed
    private String name;

    //remove this doc after 8 days
    @Indexed(expireAfterSeconds=691200)
    Date exp;

    @Indexed
    private Long date;
    private Double high;
    private Double low;
    private Double last;
    private Double close;
    private Double volume;

    //changed for 2 minutes
    private Double twoChanged;
    //changed for 3 minutes
    private Double threeChanged;
    //changed for 4 minutes
    private Double fourChanged;

    //changed for 5 minutes
    private Double fiveChanged;

    //changed for 10 minutest
    private Double tenChanged;
    //changed for 30 minutes
    private Double thirtyChanged;
    //changed for 1 hour
    private Double hourChanged;

    //changed for 3 hour
    private Double threeHourChanged;

    //changed for 6 hours
    private Double sixHourChanged;
    private Double twentyHourChanged;




    private Double changed24;

    private Double changed3day;
    private Double changed5day;

    private Double changed7day;



    private Double baseVolume;
    private Double weightedAverage;


    //changed for 1 minutes
    private Double oneChanged;


    private Double oneChangedVolume;


    //changed for 2 volume minutes
    private Double twoChangedVolume;
    //changed for 3  minutes
    private Double threeChangedVolume;
    //changed for 4 minutes
    private Double fourChangedVolume;


    private Double fiveChangedVolume;
    private Double tenChangedVolume;


    //changed for 30 minutes
    private Double thirtyChangedVolume;
    //changed for 1 hour
    private Double hourChangedVolume;

    //changed for 3 hour
    private Double threeHourChangedVolume;

    //changed for 6 hours
    private Double sixHourChangedVolume;
    //12 hours
    private Double twentyHourChangedVolume;


    private Double changed24Volume;
    private Double changed3dayVolume;
    private Double changed5dayVolume;
    private Double changed7dayVolume;


}
