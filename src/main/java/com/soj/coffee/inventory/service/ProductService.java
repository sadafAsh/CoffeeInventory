package com.soj.coffee.inventory.service;

import com.soj.coffee.inventory.model.Product;
import com.soj.coffee.inventory.util.InventoryResponse;
import com.soj.coffee.inventory.util.Resource;

import java.util.List;

public interface ProductService {
    public List<Resource<Product>> findAll();
    public Resource<Product> getProductById(long id);
    public Resource<InventoryResponse> addProduct(Product product);
    public Resource<InventoryResponse> deleteProduct(long id);
    public Resource<InventoryResponse> updateProduct(Long id, Product product);
}
