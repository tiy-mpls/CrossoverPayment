package com.theironyard;

import java.math.BigDecimal;

/**
 * Created by kdrudy on 10/28/16.
 */
public class FinalCost {
    BigDecimal subtotal;
    String zip;
    BigDecimal taxRate;
    BigDecimal total;

    public FinalCost() {

    }

    public FinalCost(BigDecimal subtotal, String zip, BigDecimal taxRate) {
        this.subtotal = subtotal;
        this.zip = zip;
        this.taxRate = taxRate;

        this.total = this.subtotal.add(this.subtotal.multiply(this.taxRate.divide(new BigDecimal("100"))));
    }

    public FinalCost(BigDecimal subtotal, String zip, BigDecimal taxRate, BigDecimal total) {
        this.subtotal = subtotal;
        this.zip = zip;
        this.taxRate = taxRate;
        this.total = total;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
