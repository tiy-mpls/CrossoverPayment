package com.theironyard;

import java.math.BigDecimal;

/**
 * Created by kdrudy on 10/27/16.
 */
public class Product {

    int id;
    String name;
    String description;
    BigDecimal price;
    String imageName;

    public Product() {

    }

    public Product(int id, String name, String description, BigDecimal price, String imageName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageName = imageName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
