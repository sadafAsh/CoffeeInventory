package com.soj.coffee.inventory.service;

import com.soj.coffee.inventory.model.Inventory;
import com.soj.coffee.inventory.util.InventoryResponse;
import com.soj.coffee.inventory.util.Resource;

import java.util.List;

public interface InventoryService {

    public List<Resource<Inventory>> getAll();
    public Resource<Inventory> getInventory(long id);
    public Resource<InventoryResponse> addInventory(Inventory inventory);
    public Resource<InventoryResponse> deleteInventory(long id);
public Resource<InventoryResponse> saveInventory(long productId, int qty,Inventory inventory );
}
