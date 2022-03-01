package com.soj.coffee.inventory.service;

import com.soj.coffee.inventory.model.Supplier;
import com.soj.coffee.inventory.util.InventoryResponse;
import com.soj.coffee.inventory.util.Resource;

import java.util.List;

public interface SupplierService {

    public List<Resource<Supplier>> getAll();

    public Resource<Supplier> getSupplier(long id);

    public Resource<InventoryResponse> addSupplier(Supplier supplier);

    public Resource<InventoryResponse> deleteSupplier(long id);

    public Resource<InventoryResponse> updateSupplier(long id, Supplier supplier);

 }
