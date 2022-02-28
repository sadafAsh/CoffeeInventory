package com.soj.coffee.inventory.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "product")

public class Product implements Serializable {
   public static final String OBJECT_TYPE="Product";
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "productName")
    private String name;
    @Column(name = "price")
    private int price;
    @Column(name = "ingredient")
    private String ingredient;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
}
