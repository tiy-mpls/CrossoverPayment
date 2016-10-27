package com.theironyard;

import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by kdrudy on 10/27/16.
 */
public class MainTest {


    @BeforeClass
    public static void setUp() throws Exception {
        Main.loadProducts();
    }

    @Test
    public void changeProductQuantityInCart() throws Exception {
        Main.cart.clear();
        Main.addProductToCart(1);
        Main.addProductToCart(2);

        Main.changeProductQuantityInCart(1, 3);

        assertTrue(Main.cart.size() == 2);
        assertTrue(Main.cart.get(1) == 3);
        assertTrue(Main.cart.get(2) == 1);

    }

    @Test
    public void removeProductFromCart() throws Exception {
        Main.cart.clear();
        Main.addProductToCart(1);
        Main.addProductToCart(2);
        Main.addProductToCart(3);

        Main.removeProductFromCart(2);

        assertTrue(Main.cart.size() == 2);
        assertFalse(Main.cart.containsKey(2));
        assertTrue(Main.cart.containsKey(1));
        assertTrue(Main.cart.containsKey(3));
    }

    @Test
    public void addProductToCart() throws Exception {
        Main.cart.clear();
        Main.addProductToCart(1);
        assertTrue(Main.cart.size() == 1);
        assertTrue(Main.cart.get(1) == 1);

        Main.addProductToCart(1);
        assertTrue(Main.cart.size() == 1);
        assertTrue(Main.cart.get(1) == 2);
    }

    @Test
    public void getProduct() throws Exception {
        Product product = Main.getProduct(1);
        assertTrue(product != null);
        assertTrue(product.getPrice().equals(new BigDecimal("10.39")));
    }

    @Test
    public void loadProducts() throws Exception {
        assertTrue(Main.products.size() == 3);
    }

}