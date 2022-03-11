package com.soj.coffee.inventory.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.CriteriaBuilder;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class InventoryTest {

    @Test
    void testToGetInventoryId() {
        Inventory inventory=new Inventory();
        inventory.setId(1l);
        Assertions.assertEquals(1,inventory.getId());
    }

    @Test
    void getQuantity() {
        Inventory inventory=new Inventory();
        inventory.setQuantity(3);
        Assertions.assertEquals(3,inventory.getQuantity());
    }

    @Test
    void testToGetProduct() {
        Inventory inventory=new Inventory();
        Product product=new Product();
        product.setId(1l);
        inventory.setProduct(product);
        Assertions.assertEquals(product,inventory.getProduct());
    }

}