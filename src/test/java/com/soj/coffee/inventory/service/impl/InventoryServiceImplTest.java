package com.soj.coffee.inventory.service.impl;

import com.soj.coffee.inventory.model.Inventory;
import com.soj.coffee.inventory.model.Product;
import com.soj.coffee.inventory.repository.InventoryRepository;
import com.soj.coffee.inventory.repository.ProductRepository;
import com.soj.coffee.inventory.service.InventoryService;
import com.soj.coffee.inventory.util.InventoryRequest;
import com.soj.coffee.inventory.util.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static com.soj.coffee.inventory.model.Product.OBJECT_TYPE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class InventoryServiceImplTest {
    @Autowired
    private InventoryService service;
    @MockBean
    private InventoryRepository repository;


    @Test
    void testToGetAllInventory() {
        Inventory inventory = new Inventory();
        inventory.setId(1l);
        List<Inventory> inventories = new ArrayList<>();
 inventories.add(inventory);
        List<Resource<Inventory>> list = new ArrayList<>();
        Resource<Inventory> resources = new Resource<>(1l, OBJECT_TYPE, inventory);
        when(repository.findAll()).thenReturn(inventories);
        List<Resource<Inventory>> resource = service.getAll();
        list.add(resources);
        Assertions.assertEquals(1,inventories.size());
        Assertions.assertEquals(1, list.size());

    }

    @Test
    void testToGetInventoryById() {
        Inventory inventory = new Inventory();
        inventory.setId(1l);
        when(repository.findById(1l)).thenReturn(java.util.Optional.of(inventory));
        Resource resource = service.getInventory(1l);
        Assertions.assertEquals(1l, resource.getId());
    }

    @Test
    void testToGetExceptionInventoryById(){
        Assertions.assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
           Inventory  inventory=new Inventory();
           inventory.setId(1l);
           when(repository.findById(2l)).thenReturn(java.util.Optional.of(inventory));
           Resource resource1=service.getInventory(1l);
            }
        });
    }

    @Test
    void testToAddInventory() {
        Inventory inventory = new Inventory();
        inventory.setId(1l);
        when(repository.saveAndFlush(any())).thenReturn(inventory);
        Resource resource1 = service.addInventory(inventory);
        Assertions.assertEquals(1l, resource1.getId());
    }

    @Test
    void testToDeleteInventoryById() {
        Inventory inventory = new Inventory();
        inventory.setId(1l);
        doNothing().when(repository).deleteById(1l);
        Resource resource1 = service.deleteInventory(1l);
        Assertions.assertEquals(1l, resource1.getId());
    }

    @Test
    void testToSaveInventoryByExistingProductId() {
        Inventory inventory = new Inventory();
        inventory.setId(1l);
        Product product = new Product();
        product.setId(1l);
        when(repository.findFirstByProductId(1l)).thenReturn(inventory);
        when(repository.findById(1l)).thenReturn(java.util.Optional.of(inventory));
        when(repository.saveAndFlush(any())).thenReturn(inventory);
        Resource resource1 = service.saveInventory(1l, 1, inventory);
        verify(repository, times(1)).saveAndFlush(inventory);

    }

    @Test
    void testToSaveNewInventoryWhenProductIdISNull() {
        Inventory inventory = new Inventory();
        Product product = new Product();
        when(repository.findFirstByProductId(0)).thenReturn(inventory);
        when(repository.saveAndFlush(any())).thenReturn(inventory);
        inventory.setQuantity(1);
        inventory.setId(1l);
        product.setId(1l);
        inventory.setProduct(product);
        Resource resource1 = service.saveInventory(1l, 1, inventory);

        Assertions.assertEquals(1, resource1.getId());
    }
}