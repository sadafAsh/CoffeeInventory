package com.soj.coffee.inventory.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soj.coffee.inventory.model.Product;
import com.soj.coffee.inventory.model.Supplier;
import com.soj.coffee.inventory.repository.ProductRepository;
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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
 class ProductIntegrationtTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private ProductRepository repository;

    @Test
    void testToGetPtoductById() throws Exception {
        Product product=new Product();
        product.setId(1l);
        given(repository.findById(1l)).willReturn(java.util.Optional.of(product));
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/product/1")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1l));
    }
    @Test
    void testToFindTheListOfProduct() throws Exception {
        Product product=new Product();
        product.setId(1l);
        List<Product> products=new ArrayList<>();
        products.add(product);
        given(repository.findAll()).willReturn(products);
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/product")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1l));
    }

    @Test
    void testToDeleteTheProductById() throws Exception {
        Product product=new Product();
        product.setId(1l);
        doNothing().when(repository).deleteById(1l);
        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/product/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1l));
    }

    @Test
    void testToAddANewProduct() throws Exception {
       Product product= new Product();
       product.setId(1l);
        Stream<String> files= Files.lines(Path.of("src/test/resources/product/product.json"));
        String data=files.collect(Collectors.joining("\n"));
        given(repository.saveAndFlush(any())).willReturn(product);
       mvc.perform(MockMvcRequestBuilders.post("/api/v1/product")
               .content(data)
       .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1l));

    }

    @Test
    void testToUpdateAProductById() throws IOException {
        Product product=new Product();
        product.setId(1l);
        Stream<String> files= Files.lines(Path.of("src/test/resources/product/product.json"));
        String data=files.collect(Collectors.joining("\n"));
        when(repository.findById(1l)).thenReturn(java.util.Optional.of(product));
        when(repository.saveAndFlush(any())).thenReturn(product);
        try {
            mvc.perform(MockMvcRequestBuilders.put("/api/v1/product/1")
                    .content(data)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1l));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
