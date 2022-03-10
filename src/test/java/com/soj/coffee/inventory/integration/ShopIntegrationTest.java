package com.soj.coffee.inventory.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soj.coffee.inventory.model.Shop;
import com.soj.coffee.inventory.repository.ShopRepository;
import com.soj.coffee.inventory.service.ShopService;
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

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.soj.coffee.inventory.model.Shop.OBJECT_TYPE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
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
        Shop shop = new Shop();
        shop.setId(1l);
        shop.setName("morrison");
        Resource resource1 = new Resource(1l, OBJECT_TYPE, shop);
        given(repository.findById(1l)).willReturn(java.util.Optional.of(shop));
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/shop/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.attribute.name").value("morrison"));

    }

    @Test
    void testToDeleteShopBYId() throws Exception {
        Shop shop = new Shop();
        shop.setId(1l);
        doNothing().when(repository).deleteById(1l);
        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/shop/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1l));

    }

    @Test
    void testToFindAllTheShop() throws Exception {
        Shop shop = new Shop();
        List<Shop> shops = new ArrayList<>();
        shop.setId(1l);
        shops.add(shop);
        given(repository.findAll()).willReturn(shops);
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/shop")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1l));
    }

    @Test
    void testToCreateANewShop() throws Exception {
        Shop shop=new Shop();
        shop.setId(1l);
        ObjectMapper object=new ObjectMapper();
        String jsonStr=object.writeValueAsString(shop);
        given(repository.saveAndFlush(any())).willReturn(shop);
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/shop")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonStr))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1l));
    }

    @Test
    void testToUpdateAnExistingShopById() throws Exception {
        Shop shop=new Shop();
        shop.setId(1l);
        Stream<String> lines= Files.lines(Paths.get("src/test/resources/shop/shop.json"));
        String data=lines.collect(Collectors.joining("\n"));
        given(repository.findById(1l)).willReturn(java.util.Optional.of(shop));
        given(repository.saveAndFlush(any())).willReturn(shop);
        mvc.perform(MockMvcRequestBuilders.put("/api/v1/shop/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(data))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1l));
    }

}
