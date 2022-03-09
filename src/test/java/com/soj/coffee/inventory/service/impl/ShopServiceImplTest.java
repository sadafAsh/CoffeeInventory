package com.soj.coffee.inventory.service.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.soj.coffee.inventory.model.Shop;
import com.soj.coffee.inventory.repository.ShopRepository;
import com.soj.coffee.inventory.service.ShopService;
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

import static com.soj.coffee.inventory.model.Shop.OBJECT_TYPE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ShopServiceImplTest {

    @Autowired
    private ShopService service;

    @MockBean
    private ShopRepository repository;

    @Test
    void testToGetAllShop() {
        Shop shop =new Shop();
        List<Shop> shops=new ArrayList<>();
        shops.add(shop);
        Resource<Shop> resource=new Resource<>(1l,OBJECT_TYPE,shop);

        when(repository.findAll()).thenReturn(shops);
        List<Resource<Shop>> resource1=service.getAll();
        resource1.add(resource);
        Assertions.assertEquals(1,shops.size());
       Assertions.assertEquals(2,resource1.size());


    }

    @Test
    void testToGetShopById() {
        Shop shop=new Shop();
        shop.setId(1l);
        when(repository.findById(1l)).thenReturn(java.util.Optional.of(shop));
        Resource<Shop> shop1=service.getShop(1l);
        Assertions.assertEquals(1l,shop1.getId());
    }

    @Test
    void testToAddShop() {
        Shop shop=new Shop();
        shop.setId(1l);
        when(repository.saveAndFlush(any())).thenReturn(shop);
        Resource<InventoryResponse> resource1=service.addShop(shop);
        Assertions.assertEquals(1l,resource1.getId());
        verify(repository,times(1)).saveAndFlush(shop);
    Assertions.assertEquals("Shop",resource1.getType());
    }

    @Test
    void testToDeleteShopById() {
        Shop shop=new Shop();
        shop.setId(1l);
       doNothing(). when(repository).deleteById(1l);
       Resource resource=service.deleteShop(1l);
       Assertions.assertEquals(1l,resource.getId());

    }

    @Test
    void testToUpdateShopById() {

        Shop shop=new Shop();
        shop.setId(1l);
        when(repository.findById(1l)).thenReturn(java.util.Optional.of(shop));
        when(repository.saveAndFlush(any())).thenReturn(shop);
        Resource resource1=service.updateShop(1l,shop);
        verify(repository,times(1)).saveAndFlush(shop);



   }
}