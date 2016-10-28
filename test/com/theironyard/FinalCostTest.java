package com.theironyard;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by kdrudy on 10/28/16.
 */
public class FinalCostTest {

    @Test
    public void constructorTest() throws Exception {
        FinalCost finalCost = new FinalCost(new BigDecimal("100.00"), "55555", new BigDecimal("7.775"));

        System.out.println(finalCost.getTotal());
        assertTrue(finalCost.getTotal().equals(new BigDecimal("107.7750000")));
    }
}