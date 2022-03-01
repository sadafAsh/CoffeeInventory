package com.soj.coffee.inventory.controller;

import com.soj.coffee.inventory.model.Supplier;
import com.soj.coffee.inventory.service.SupplierService;
import com.soj.coffee.inventory.util.InventoryRequest;
import com.soj.coffee.inventory.util.InventoryResponse;
import com.soj.coffee.inventory.util.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public List<Resource<Supplier>> findAll(){
        return supplierService.getAll();
    }

    @GetMapping("/{id}")
    public Resource<Supplier> getSupplierById(@PathVariable long id){
        return supplierService.getSupplier(id);
    }

    @PostMapping
    public Resource<InventoryResponse> addSupplier(@RequestBody InventoryRequest<Supplier> request){
        Supplier supplier=request.getAttribute();
        return supplierService.addSupplier(supplier);
    }

    @DeleteMapping("/{id}")
    public Resource<InventoryResponse> deleteSupplierById(@PathVariable long id){
        return supplierService.deleteSupplier(id);
    }

    @PutMapping("/{id}")
    public Resource<InventoryResponse> updateSupplier(@PathVariable long id,@RequestBody InventoryRequest<Supplier> request){
        Supplier supplier=request.getAttribute();
        return supplierService.updateSupplier(id,supplier);
    }
}
