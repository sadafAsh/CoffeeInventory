package com.soj.coffee.inventory.model;

import javax.persistence.*;

@Entity
@Table(name = "supplier")
public class Supplier {
    public static final String OBJECT_TYPE="Supplier";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "supplierName")
    private String name;

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
}
