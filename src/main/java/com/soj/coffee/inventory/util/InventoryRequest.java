package com.soj.coffee.inventory.util;

public class InventoryRequest<T> {
    private Long id;
    private String type;
    private T attribute;

    public InventoryRequest(Long id, String type, T attribute) {
        this.id = id;
        this.type = type;
        this.attribute = attribute;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public T getAttribute() {
        return attribute;
    }

    public void setAttribute(T attribute) {
        this.attribute = attribute;
    }
}
