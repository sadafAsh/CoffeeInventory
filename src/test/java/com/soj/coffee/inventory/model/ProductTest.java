package com.soj.coffee.inventory.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ProductTest {

    @Test
    void testToGetProductId() {
        Product product=new Product();
        product.setId(1l);
        Assertions.assertEquals(1,product.getId());
    }

    @Test
    void testToGetProductName() {
        Product product=new Product();
        product.setName("ground coffee");
        Assertions.assertEquals("ground coffee",product.getName());
    }

    @Test
    void testToGetTheProductPrice() {
        Product product=new Product();
        product.setPrice(200);
        Assertions.assertEquals(200,product.getPrice());
    }

    @Test
    void testToGetIngredient() {
        Product product=new Product();
        product.setIngredient("ready to brew");
        Assertions.assertEquals("ready to brew",product.getIngredient());
    }

}