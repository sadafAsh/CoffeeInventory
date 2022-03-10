package com.soj.coffee.inventory.integration;


import com.soj.coffee.inventory.model.Inventory;
import com.soj.coffee.inventory.model.Product;
import com.soj.coffee.inventory.repository.InventoryRepository;
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

import javax.persistence.criteria.CriteriaBuilder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc

 class InventoryIntegrationTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private InventoryRepository repository;


    @Test
    void testToGetInventoryById() throws Exception {
        Inventory inventory=new Inventory();
        inventory.setId(1l);
        given(repository.findById(1l)).willReturn(java.util.Optional.of(inventory));
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/inventory/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1l));
    }
    @Test
    void testToFindTheListOfInventory() throws Exception {
        Inventory inventory=new Inventory();
        inventory.setId(1l);
        List<Inventory> list=new ArrayList<>();
        list.add(inventory);
        given(repository.findAll()).willReturn(list);
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/inventory")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1l));
    }

    @Test
    void testToDeleteTheInventoryById() throws Exception {
        Inventory inventory=new Inventory();
        inventory.setId(1l);
        doNothing().when(repository).deleteById(1l);
        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/inventory/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1l));
    }

    @Test
    void testToAddANewInventory() throws Exception {
        Inventory inventory=new Inventory();
        inventory.setId(1l);
        Stream<String> files= Files.lines(Path.of("src/test/resources/inventory/inventory.json"));
        String data=files.collect(Collectors.joining("\n"));
        given(repository.saveAndFlush(any())).willReturn(inventory);
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/inventory")
                .content(data)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(0));

    }

}
