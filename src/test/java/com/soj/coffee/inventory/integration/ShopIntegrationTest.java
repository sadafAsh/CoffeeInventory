package com.soj.coffee.inventory.integration;

import com.soj.coffee.inventory.model.Shop;
import com.soj.coffee.inventory.repository.ShopRepository;
import com.soj.coffee.inventory.util.Resource;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.swing.*;

import static com.soj.coffee.inventory.model.Shop.OBJECT_TYPE;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ShopIntegrationTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private ShopRepository repository;

    @Test
     void testToGetShopById() throws Exception {
        Shop shop=new Shop();
        shop.setId(1l);
        shop.setName("morrison");
        Resource resource1=new Resource(1l,OBJECT_TYPE,shop);
        given(repository.findById(1l)).willReturn(java.util.Optional.of(shop));
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/shop/1")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.attribute.name").value("morrison"));

    }

}
