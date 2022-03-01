package com.soj.coffee.inventory.controller;

import com.soj.coffee.inventory.model.Inventory;
import com.soj.coffee.inventory.service.InventoryService;
import com.soj.coffee.inventory.util.InventoryRequest;
import com.soj.coffee.inventory.util.InventoryResponse;
import com.soj.coffee.inventory.util.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public List<Resource<Inventory>> findAll(){
        return inventoryService.getAll();
    }

    @GetMapping("/{id}")
    public Resource<Inventory> getInventoryById(@PathVariable long id){
        return inventoryService.getInventory(id);
    }

    @PostMapping
    public Resource<InventoryResponse> addInventory(@RequestBody InventoryRequest<Inventory> request){
        Inventory inventory=request.getAttribute();
        return inventoryService.saveInventory(inventory.getProduct().getId(),inventory.getQuantity(),inventory);
    }

    @DeleteMapping("/{id}")
    public Resource<InventoryResponse> deleteInventoryById(@PathVariable long id){
        return inventoryService.deleteInventory(id);
    }


}
