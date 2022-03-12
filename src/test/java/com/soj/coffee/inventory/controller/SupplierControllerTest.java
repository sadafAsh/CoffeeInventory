package com.soj.coffee.inventory.controller;

import com.soj.coffee.inventory.model.Supplier;
import com.soj.coffee.inventory.service.SupplierService;
import com.soj.coffee.inventory.util.InventoryResponse;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SupplierControllerTest {

    @Autowired
    private SupplierController controller;

    @MockBean
    private SupplierService service;

    @Test
    void testToFindAll() {
        Supplier supplier=new Supplier();
        Resource<Supplier> resource=new Resource<>(1l,OBJECT_TYPE,supplier);
        Resource<Supplier> resource1=new Resource<>(1l,OBJECT_TYPE,supplier);
        List<Resource<Supplier>> resources=new ArrayList<>();
        when(service.getAll()).thenReturn(resources);
        List<Resource<Supplier>> resource2=controller.findAll();
        resources.add(resource);
        resources.add(resource);
        Assertions.assertEquals(resource2,resources);
        Assertions.assertEquals(2,resources.size());
    }

    @Test
    void testToGetSupplierById() {
        Supplier supplier= new Supplier();
        Resource<Supplier> resource=new Resource<>(1l,OBJECT_TYPE,supplier);
        when(service.getSupplier(1l)).thenReturn(resource);
        Resource resource1=controller.getSupplierById(1l);
        Assertions.assertEquals(1l,resource1.getId());
    }

    @Test
    void testTOAddSupplier() {
        Supplier supplier=new Supplier();
        Resource<Supplier> request=new Resource<>(1l,OBJECT_TYPE,supplier);
        InventoryResponse response=new InventoryResponse(1l,"create successful");
       Resource<InventoryResponse> resource=new Resource(1l,"Supplier",response);
        when(service.addSupplier(any())).thenReturn(resource);
        Resource resource1=controller.addSupplier(request);
        Assertions.assertEquals(resource1,resource);
    }

    @Test
    void testToDeleteSupplierById() {
        Supplier supplier=new Supplier();
        InventoryResponse response=new InventoryResponse(1l,"Delete message");
        Resource<InventoryResponse> resource=new Resource<>(1l,OBJECT_TYPE,response);
        when(service.deleteSupplier(1l)).thenReturn(resource);
        Resource resource1=controller.deleteSupplierById(1l);
        Assertions.assertEquals(resource1,resource);
    }

    @Test
    void testToUpdateSupplierById() {
    Supplier supplier=new Supplier();
    Resource<Supplier> request=new Resource<>(1l,OBJECT_TYPE,supplier);
        InventoryResponse response=new InventoryResponse(1l,"update message");
        Resource<InventoryResponse> resource=new Resource<>(1l,OBJECT_TYPE,response);
    when(service.updateSupplier(anyLong(),any())).thenReturn(resource);
    Resource resource1=controller.updateSupplier(1l,request);
        Assertions.assertEquals(resource1,resource);
        Assertions.assertEquals(1l,resource1.getId());
        Assertions.assertEquals(resource1.getAttribute(),response);

    }
}