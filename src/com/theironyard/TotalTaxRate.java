package com.theironyard;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by kdrudy on 10/28/16.
 */
public class TotalTaxRate {
    BigDecimal totalRate;
    ArrayList<TaxRate> rates;

    public TotalTaxRate() {

    }

    public TotalTaxRate(BigDecimal totalRate, ArrayList<TaxRate> rates) {
        this.totalRate = totalRate;
        this.rates = rates;
    }

    public BigDecimal getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(BigDecimal totalRate) {
        this.totalRate = totalRate;
    }

    public ArrayList<TaxRate> getRates() {
        return rates;
    }

    public void setRates(ArrayList<TaxRate> rates) {
        this.rates = rates;
    }
}
