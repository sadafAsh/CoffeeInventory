package com.soj.coffee.inventory.controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.soj.coffee.inventory.model.Inventory;
import com.soj.coffee.inventory.model.Product;
import com.soj.coffee.inventory.service.InventoryService;
import com.soj.coffee.inventory.util.InventoryRequest;
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

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.soj.coffee.inventory.model.Inventory.OBJECT_TYPE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class InventoryControllerTest {

    @Autowired
    private InventoryController controller;

    @MockBean
    private InventoryService service;

    @Test
    void testToFindAllInventory() {
        Inventory inventory=new Inventory();
        Resource<Inventory> resource=new Resource<>(1l,OBJECT_TYPE,inventory);
        List<Resource<Inventory>> resources=new ArrayList<>();
        when(service.getAll()).thenReturn(resources);
        List<Resource<Inventory>> resources1=controller.findAll();
        resources.add(resource);
        Assertions.assertEquals(1,resources.size());
       Assertions.assertEquals(resources,resources1);
    }

    @Test
    void getInventoryById() {
        Inventory inventory= new Inventory();
        Resource<Inventory> resource=new Resource<>(1l,OBJECT_TYPE,inventory);
        when(service.getInventory(1l)).thenReturn(resource);
        Resource resource1=controller.getInventoryById(1l);
        Assertions.assertEquals(1l,resource1.getId());
    }

    @Test
    void addInventory() {
        Inventory inventory=new Inventory();
        Product product=new Product();
        product.setId(1l);
        inventory.setProduct(product);
        inventory.setQuantity(1);
        InventoryRequest<Inventory> request=new InventoryRequest<>(1l,OBJECT_TYPE,inventory);
        InventoryResponse response=new InventoryResponse(1l,"save successfully");
        Resource<InventoryResponse> resource=new Resource<>(1l,OBJECT_TYPE,response);
        when(service.saveInventory(anyLong(),anyInt(),any())).thenReturn(resource);
        Resource resource1=controller.addInventory(request);
        verify(service,times(1)).saveInventory(1l,1,inventory);
        Assertions.assertEquals(resource1,resource);
    }

    @Test
    void deleteInventoryById() {
        Inventory inventory=new Inventory();
        InventoryResponse response=new InventoryResponse(1l,"delete successfully");
        Resource<InventoryResponse> resource=new Resource<>(1l,OBJECT_TYPE,response);
        when(service.deleteInventory(1l)).thenReturn(resource);
        Resource resource1=controller.deleteInventoryById(1l);
        Assertions.assertEquals(1l,resource1.getId());

    }
}