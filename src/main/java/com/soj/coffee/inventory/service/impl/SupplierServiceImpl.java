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
import java.util.stream.Collectors;

import static com.soj.coffee.inventory.model.Supplier.OBJECT_TYPE;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public List<Resource<Supplier>> getAll() {
        List<Resource<Supplier>> resources=new ArrayList<>();
         supplierRepository.findAll().stream().map(x->{
             Resource<Supplier> resource=new Resource<>(x.getId(),OBJECT_TYPE,x);
        resources.add(resource);
        return x;
         })
                 .collect(Collectors.toList());
         return resources;
    }

    @Override
    public Resource<Supplier> getSupplier(long id) {
        Supplier supplier= supplierRepository.findById(id).get();
    return new Resource<>(supplier.getId(),OBJECT_TYPE,supplier);

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
        Supplier existingSupplier=supplierRepository.findById(id).get();
        BeanUtils.copyProperties(supplier,existingSupplier,"supplier_id");
        Supplier supplier1=supplierRepository.saveAndFlush(existingSupplier);
        InventoryResponse response=new InventoryResponse(supplier1.getId(),"update successfully");
        return new Resource<>(supplier1.getId(),OBJECT_TYPE,response);
    }
}
