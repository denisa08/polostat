package ru.denisa.arbitrage.model;

import lombok.Data;

/**
 * Created by d.aleksandrov on 04.01.2018.

 Арибтражный класс
 */
@Data
public class ArbitragePair {

    private String name;

    private double lastPoloPrice;
    private double lastBtxPrice;

    private double lastPoloVolume;
    private double lastBtxVolume;


    //здесь будет отображаться разница в процентах
    private double priceDiff;
    private double volumeDiff;




}
