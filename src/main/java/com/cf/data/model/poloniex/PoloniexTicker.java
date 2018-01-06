package com.cf.data.model.poloniex;

import com.google.gson.Gson;

import java.math.BigDecimal;

/**
 *
 * @author David
 */
public class PoloniexTicker
{
    public final Double last;


    public final BigDecimal lowestAsk;
    public final BigDecimal highestBid;
    public final BigDecimal percentChange;
    public final BigDecimal baseVolume;
    public final BigDecimal quoteVolume;

    //разница с битрексом
    public double bittrexDifference;
    public String name;


    public PoloniexTicker(Double last, BigDecimal lowestAsk, BigDecimal highestBid, BigDecimal percentChange, BigDecimal baseVolume, BigDecimal quoteVolume)
    {
        this.last = last;
        this.lowestAsk = lowestAsk;
        this.highestBid = highestBid;
        this.percentChange = percentChange;
        this.baseVolume = baseVolume;
        this.quoteVolume = quoteVolume;
    }

    @Override
    public String toString()
    {
        return new Gson().toJson(this);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLast() {
        return last;
    }

    public BigDecimal getLowestAsk() {
        return lowestAsk;
    }

    public BigDecimal getHighestBid() {
        return highestBid;
    }

    public BigDecimal getPercentChange() {
        return percentChange;
    }

    public BigDecimal getBaseVolume() {
        return baseVolume;
    }

    public BigDecimal getQuoteVolume() {
        return quoteVolume;
    }


    public double getBittrexDifference() {
        return bittrexDifference;
    }

    public void setBittrexDifference(double bittrexDifference) {
        this.bittrexDifference = bittrexDifference;
    }


}
