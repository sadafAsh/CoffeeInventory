package com.soj.coffee.inventory.model;

import javax.persistence.*;

@Entity
@Table(name = "shop")
public class Shop {
      public static final String OBJECT_TYPE="Shop";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "shopName")
    private String shopName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
