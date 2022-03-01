package com.soj.coffee.inventory.service.impl;

import com.soj.coffee.inventory.model.Inventory;
import com.soj.coffee.inventory.model.Product;
import com.soj.coffee.inventory.repository.InventoryRepository;
import com.soj.coffee.inventory.service.InventoryService;
import com.soj.coffee.inventory.service.ProductService;
import com.soj.coffee.inventory.util.InventoryResponse;
import com.soj.coffee.inventory.util.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


import static com.soj.coffee.inventory.model.Inventory.OBJECT_TYPE;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ProductService productService;

    @Override
    public List<Resource<Inventory>> getAll() {
        List<Resource<Inventory>> resources=new ArrayList<>();
        for (Inventory x:inventoryRepository.findAll()){
            Resource<Inventory> resource=new Resource<>(x.getId(),OBJECT_TYPE,x);
            resources.add(resource);
        }
        return resources;
    }

    @Override
    public Resource<Inventory> getInventory(long id) {
        Inventory inventory=inventoryRepository.findById(id).get();
        return new Resource<>(inventory.getId(),OBJECT_TYPE,inventory);
    }

    @Override
    public Resource<InventoryResponse> addInventory(Inventory inventory) {
       Inventory inventory1=inventoryRepository.saveAndFlush(inventory);
        InventoryResponse response=new InventoryResponse(inventory1.getId(),"create successfully");
        return new Resource<>(inventory1.getId(),OBJECT_TYPE,response);
    }

    @Override
    public Resource<InventoryResponse> deleteInventory(long id) {
            inventoryRepository.deleteById(id);
            InventoryResponse response=new InventoryResponse(id,"delete successfully");
        return new Resource<>(id,OBJECT_TYPE,response);
    }

    @Override

    public Resource<InventoryResponse> saveInventory(long productId, int qty,Inventory inventory1) {
        Inventory inventory=inventoryRepository.findFirstByProductId(productId);

        if (inventory!=null){
            inventory.setQuantity(inventory.getQuantity()+qty);




        }
        else {

           inventory=new Inventory();
            inventory.setQuantity(qty);
           // Product product=productService.getProductById(inventory.getProduct().getId()).getAttribute();
         Product product=new Product();
         product.setName(inventory1.getProduct().getName());
         product.setIngredient(inventory1.getProduct().getIngredient());
         product.setPrice(inventory1.getProduct().getPrice());
            inventory.setProduct(product);

        }


        Inventory inventory2=inventoryRepository.saveAndFlush(inventory);
        InventoryResponse response=new InventoryResponse(inventory2.getId(),"save successfully");
        return new Resource<>(inventory1.getId(),OBJECT_TYPE,response);
    }



}
