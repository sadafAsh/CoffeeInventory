package com.soj.coffee.inventory.controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.soj.coffee.inventory.model.Shop;
import com.soj.coffee.inventory.service.ShopService;
import com.soj.coffee.inventory.util.InventoryRequest;
import com.soj.coffee.inventory.util.InventoryResponse;
import com.soj.coffee.inventory.util.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ShopControllerTest {

    @Autowired
    private ShopController controller;

    @MockBean
    private ShopService service;

    @Test
    void testToFindAllShopFromDb() {
        Shop shop = new Shop();
        List<Resource<Shop>> resources = new ArrayList<>();
        InventoryRequest<Shop> request = new InventoryRequest<>(1l, "Shop", shop);
        Resource<Shop> resource = new Resource(1l, "Shop", request);
        when(service.getAll()).thenReturn(resources);
        List<Resource<Shop>> resource1 = controller.findAllShop();
        resources.add(resource);
        Assertions.assertEquals(1, resources.size());
        Assertions.assertEquals(resources, resource1);

    }

    @Test
    void testToGetShopByIdFromDb() {
        Shop shop = new Shop();
        shop.setId(1l);
        shop.setName("sunil");
        Resource<Shop> resource = new Resource<>(1l, "Shop", shop);
        resource.setAttribute(shop);
        when(service.getShop(1l)).thenReturn(resource);
        Resource resource1 = controller.getShopById(1l);
        Assertions.assertEquals(1l, resource1.getId());
        Assertions.assertEquals(shop, resource1.getAttribute());

    }

    @Test()
    void testToAddShopInDb() {
        Shop shop = new Shop();
        InventoryRequest<Shop> request = new InventoryRequest<>(1l, "Shop", shop);
        Resource resource = new Resource<>(1l, "Shop", request);
        when(service.addShop(any())).thenReturn(resource);
        Resource resource1 = controller.addShop(request);
        Assertions.assertEquals(resource1, resource);
        Assertions.assertEquals( "Shop",resource1.getType());


    }

    @Test
    void testToDeleteShopByIdFromDb() {
        Shop shop = new Shop();
        InventoryRequest<Shop> request = new InventoryRequest<>(1l, "Shop", shop);
        Resource<InventoryResponse> resource = new Resource(1l, "Shop", request);
        when(service.deleteShop(1l)).thenReturn(resource);
        Resource resource1 = controller.deleteShop(1l);
        Assertions.assertEquals(1l, resource1.getId());
        verify(service, times(1)).deleteShop(1l);
    }

    @Test
    void testToUpdateShopByIdFromDb() {
        Shop shop = new Shop();
        InventoryRequest<Shop> request = new InventoryRequest<>(1l, "Shop", shop);
        InventoryResponse response = new InventoryResponse(1l, "update successful");
        Resource<InventoryResponse> resource = new Resource<>(1l, "Shop", response);
        when(service.updateShop(anyLong(), any())).thenReturn(resource);
        Resource resource1 = controller.updateShop(1l, request);
        Assertions.assertEquals(resource1, resource);
    }
}