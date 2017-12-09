package ru.denisa.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by D.Aleksandrov on 01/07/17.
 */
@Document(collection = "Pairs")

public class Pair {




    @Id
    @Indexed
    private Long id;

    @Indexed
    private String name;

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

    //changed for 24 hours
    private Double twentyHourChanged;

    //changed for all time
    private Double allTimeChanged;


    private Double baseVolume;
    private Double weightedAverage;



    //changed for 1 minutes
    private Double oneChanged;

    public Double getTwoChanged() {
        return twoChanged;
    }

    public void setTwoChanged(Double twoChanged) {
        this.twoChanged = twoChanged;
    }

    public Double getThreeChanged() {
        return threeChanged;
    }

    public void setThreeChanged(Double threeChanged) {
        this.threeChanged = threeChanged;
    }

    public Double getFourChanged() {
        return fourChanged;
    }

    public void setFourChanged(Double fourChanged) {
        this.fourChanged = fourChanged;
    }

    public Double getTwoChangedVolume() {
        return twoChangedVolume;
    }

    public void setTwoChangedVolume(Double twoChangedVolume) {
        this.twoChangedVolume = twoChangedVolume;
    }

    public Double getThreeChangedVolume() {
        return threeChangedVolume;
    }

    public void setThreeChangedVolume(Double threeChangedVolume) {
        this.threeChangedVolume = threeChangedVolume;
    }

    public Double getFourChangedVolume() {
        return fourChangedVolume;
    }

    public void setFourChangedVolume(Double fourChangedVolume) {
        this.fourChangedVolume = fourChangedVolume;
    }



    public Double getOneChangedVolume() {
        return oneChangedVolume;
    }

    public void setOneChangedVolume(Double oneChangedVolume) {
        this.oneChangedVolume = oneChangedVolume;
    }

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

    //changed for 24 hours
    private Double twentyHourChangedVolume;

    //changed for all time
    private Double allTimeChangedVolume;






    public Double getFiveChangedVolume()
    {
        return fiveChangedVolume;
    }


    public void setFiveChangedVolume(Double fiveChangedVolume){
        this.fiveChangedVolume = fiveChangedVolume;
    }



    public Double getOneChanged() {
        return oneChanged;
    }

    public void setOneChanged(Double oneChanged) {
        this.oneChanged = oneChanged;
    }


    public Double getFiveChanged(){return fiveChanged;}
    public void setFiveChanged(Double change){this.fiveChanged =change;}

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

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getLast() {
        return last;
    }

    public void setLast(Double last) {
        this.last = last;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getBaseVolume() {
        return baseVolume;
    }

    public void setBaseVolume(Double baseVolume) {
        this.baseVolume = baseVolume;
    }

    public Double getWeightedAverage() {
        return weightedAverage;
    }

    public void setWeightedAverage(Double weightedAverage) {
        this.weightedAverage = weightedAverage;
    }

    public Double getTenChanged() {
        return tenChanged;
    }

    public void setTenChanged(Double tenChanged) {
        this.tenChanged = tenChanged;
    }

    public Double getThirtyChanged() {
        return thirtyChanged;
    }

    public void setThirtyChanged(Double thirtyChanged) {
        this.thirtyChanged = thirtyChanged;
    }

    public Double getHourChanged() {
        return hourChanged;
    }

    public void setHourChanged(Double hourChanged) {
        this.hourChanged = hourChanged;
    }

    public Double getThreeHourChanged() {
        return threeHourChanged;
    }

    public void setThreeHourChanged(Double threeHourChanged) {
        this.threeHourChanged = threeHourChanged;
    }

    public Double getSixHourChanged() {
        return sixHourChanged;
    }

    public void setSixHourChanged(Double sixHourChanged) {
        this.sixHourChanged = sixHourChanged;
    }

    public Double getTwentyHourChanged() {
        return twentyHourChanged;
    }

    public void setTwentyHourChanged(Double twentyHourChanged) {
        this.twentyHourChanged = twentyHourChanged;
    }

    public Double getAllTimeChanged() {
        return allTimeChanged;
    }

    public void setAllTimeChanged(Double allTimeChanged) {
        this.allTimeChanged = allTimeChanged;
    }

    public Double getTenChangedVolume() {
        return tenChangedVolume;
    }

    public void setTenChangedVolume(Double tenChangedVolume) {
        this.tenChangedVolume = tenChangedVolume;
    }

    public Double getThirtyChangedVolume() {
        return thirtyChangedVolume;
    }

    public void setThirtyChangedVolume(Double thirtyChangedVolume) {
        this.thirtyChangedVolume = thirtyChangedVolume;
    }

    public Double getHourChangedVolume() {
        return hourChangedVolume;
    }

    public void setHourChangedVolume(Double hourChangedVolume) {
        this.hourChangedVolume = hourChangedVolume;
    }

    public Double getThreeHourChangedVolume() {
        return threeHourChangedVolume;
    }

    public void setThreeHourChangedVolume(Double threeHourChangedVolume) {
        this.threeHourChangedVolume = threeHourChangedVolume;
    }

    public Double getSixHourChangedVolume() {
        return sixHourChangedVolume;
    }

    public void setSixHourChangedVolume(Double sixHourChangedVolume) {
        this.sixHourChangedVolume = sixHourChangedVolume;
    }

    public Double getTwentyHourChangedVolume() {
        return twentyHourChangedVolume;
    }

    public void setTwentyHourChangedVolume(Double twentyHourChangedVolume) {
        this.twentyHourChangedVolume = twentyHourChangedVolume;
    }

    public Double getAllTimeChangedVolume() {
        return allTimeChangedVolume;
    }

    public void setAllTimeChangedVolume(Double allTimeChangedVolume) {
        this.allTimeChangedVolume = allTimeChangedVolume;
    }




}
