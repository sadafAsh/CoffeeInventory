package com.soj.coffee.inventory.service;

import com.soj.coffee.inventory.model.Shop;
import com.soj.coffee.inventory.util.InventoryResponse;
import com.soj.coffee.inventory.util.Resource;

import java.util.List;

public interface ShopService {
    public List<Resource<Shop>> getAll();
    public Resource<Shop> getShop(long id);
    public Resource<InventoryResponse> addShop(Shop shop);
    public Resource<InventoryResponse> deleteShop(long id);
    public Resource<InventoryResponse> updateShop(long id, Shop shop);
}
