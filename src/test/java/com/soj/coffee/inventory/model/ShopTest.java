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
class ShopTest {

    @Test
    void testToGetId() {
        Shop shop=new Shop();
        shop.setId(1l);
        Assertions.assertEquals(1,shop.getId());
    }

    @Test
    void testToGetName() {
        Shop shop=new Shop();
        shop.setName("Morrison");
        Assertions.assertEquals("Morrison",shop.getName());
    }
}