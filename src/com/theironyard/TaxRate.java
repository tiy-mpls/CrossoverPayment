package com.theironyard;

import java.math.BigDecimal;

/**
 * Created by kdrudy on 10/28/16.
 */
public class TaxRate {
    BigDecimal rate;
    String name;
    String type;

    public TaxRate() {

    }

    public TaxRate(BigDecimal rate, String name, String type) {
        this.rate = rate;
        this.name = name;
        this.type = type;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
