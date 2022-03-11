package com.soj.coffee.inventory.service.impl;

import com.soj.coffee.inventory.model.Supplier;
import com.soj.coffee.inventory.repository.SupplierRepository;
import com.soj.coffee.inventory.service.SupplierService;
import com.soj.coffee.inventory.util.InventoryResponse;
import com.soj.coffee.inventory.util.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.soj.coffee.inventory.model.Supplier.OBJECT_TYPE;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public List<Resource<Supplier>> getAll() {
        List<Resource<Supplier>> resources=new ArrayList<>();
         supplierRepository.findAll().forEach(x->{
             Resource<Supplier> resource=new Resource<>(x.getId(),OBJECT_TYPE,x);
        resources.add(resource);

         });

         return resources;
    }

    @Override
    public Resource<Supplier> getSupplier(long id) {
         Optional<Supplier> supplier=supplierRepository.findById(id);
         if (supplier.isPresent()) {
             return new Resource<>(supplier.get().getId(), OBJECT_TYPE, supplier.get());
         }
         else{
             throw new IllegalArgumentException(id+" is not present");
         }
    }

    @Override
    public Resource<InventoryResponse> addSupplier(Supplier supplier) {
        Supplier supplier1=supplierRepository.saveAndFlush(supplier);
        InventoryResponse response=new InventoryResponse(supplier1.getId(),"create successfully");
        return new Resource<>(supplier1.getId(),OBJECT_TYPE,response);
    }

    @Override
    public Resource<InventoryResponse> deleteSupplier(long id) {
        supplierRepository.deleteById(id);
        InventoryResponse response=new InventoryResponse(id,"delete successfully");
        return new Resource<>(id,OBJECT_TYPE,response);
    }

    @Override
    public Resource<InventoryResponse> updateSupplier(long id, Supplier supplier) {
        Optional<Supplier> existingSupplier = supplierRepository.findById(id);
        BeanUtils.copyProperties(supplier, existingSupplier, "supplier_id");
        if (existingSupplier.isPresent()) {
            Supplier supplier1 = supplierRepository.saveAndFlush(supplier);
            InventoryResponse response = new InventoryResponse(supplier1.getId(), "update successfully");
            return new Resource<>(supplier1.getId(), OBJECT_TYPE, response);
        } else {
           throw  new IllegalArgumentException(id + " is not present");
        }
    }
}
