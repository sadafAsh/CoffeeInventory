package com.soj.coffee.inventory.service.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.soj.coffee.inventory.model.Supplier;
import com.soj.coffee.inventory.repository.SupplierRepository;
import com.soj.coffee.inventory.service.SupplierService;
import com.soj.coffee.inventory.util.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static com.soj.coffee.inventory.model.Supplier.OBJECT_TYPE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SupplierServiceImplTest {

    @Autowired
    private SupplierService service;
    @MockBean
    private SupplierRepository repository;

    @Test
    void testToGetAllSupplier() {
        Supplier supplier=new Supplier();
        supplier.setId(1l);
        List<Supplier> suppliers=new ArrayList<>();
        suppliers.add(supplier);
        List<Resource<Supplier>> list=new ArrayList<>();
        Resource<Supplier> resources=new Resource<>(1l,OBJECT_TYPE,supplier);
        when(repository.findAll()).thenReturn(suppliers);
       List<Resource<Supplier>>  resource=service.getAll();
       list.add(resources);
       Assertions.assertEquals(1,suppliers.size());
       Assertions.assertEquals(1,list.size());
    }

    @Test
    void testToGetSupplierById() {
        Supplier supplier=new Supplier();
        supplier.setId(1l);
        when(repository.findById(1l)).thenReturn(java.util.Optional.of(supplier));
        Resource resource=service.getSupplier(1l);
        Assertions.assertEquals(1l,resource.getId());
    }

    @Test
    void testToAddSupplier() {
        Supplier supplier=new Supplier();
        supplier.setId(1l);
        when(repository.saveAndFlush(any())).thenReturn(supplier);
        Resource resource1=service.addSupplier(supplier);
        Assertions.assertEquals(1l,resource1.getId());
    }

    @Test
    void testToDeleteSupplierById() {
        Supplier supplier=new Supplier();
        supplier.setId(1l);
        doNothing().when(repository).deleteById(1l);
        Resource resource1=service.deleteSupplier(1l);
        Assertions.assertEquals(1l,resource1.getId());
    }

    @Test
    void testToUpdateSupplierById() {
        Supplier supplier=new Supplier();
        supplier.setId(1l);
        when(repository.findById(1l)).thenReturn(java.util.Optional.of(supplier));
   when(repository.saveAndFlush(any())).thenReturn(supplier);
   Resource resource1=service.updateSupplier(1l,supplier);
   verify(repository,times(1)).saveAndFlush(supplier);

    }
}