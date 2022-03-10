package com.soj.coffee.inventory.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soj.coffee.inventory.model.Shop;
import com.soj.coffee.inventory.model.Supplier;
import com.soj.coffee.inventory.repository.SupplierRepository;
import com.soj.coffee.inventory.service.SupplierService;
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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.soj.coffee.inventory.model.Supplier.OBJECT_TYPE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc

 class SupplierIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SupplierRepository repository;

    @Test
    void testToGetSupplierById() throws Exception {
        Supplier supplier=new Supplier();
        supplier.setId(1l);
        Resource<Supplier> resource=new Resource<>(1l,OBJECT_TYPE,supplier);
resource.setId(1l);
        given(repository.findById(1l)).willReturn(java.util.Optional.of(supplier));
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/supplier/1")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1l));
    }

    @Test
    void testToFindTheListOfSupplier() throws Exception {
        Supplier supplier=new Supplier();
        supplier.setId(1l);
        List<Supplier> suppliers=new ArrayList<>();
        suppliers.add(supplier);
        given(repository.findAll()).willReturn(suppliers);
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/supplier")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1l));
    }

    @Test
    void testToDeleteTheSupplierById() throws Exception {
        Supplier supplier=new Supplier();
        supplier.setId(1l);
        doNothing().when(repository).deleteById(1l);
        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/supplier/1")
        .contentType(MediaType.APPLICATION_JSON))
.andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1l));
    }

    @Test
    void testToAddANewSupplier() throws Exception {
        Supplier supplier=new Supplier();
        supplier.setId(1l);
        ObjectMapper object=new ObjectMapper();
        String jsonStr=object.writeValueAsString(supplier);
        given(repository.saveAndFlush(any())).willReturn(supplier);
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/supplier")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonStr))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1l));
    }

    @Test
    void testToUpdateASupplierById() throws IOException {
        Supplier supplier=new Supplier();
        supplier.setId(1l);
        Stream<String> files= Files.lines(Path.of("src/test/resources/supplier/supplier.json"));
        String data=files.collect(Collectors.joining("\n"));
        when(repository.findById(1l)).thenReturn(java.util.Optional.of(supplier));
        when(repository.saveAndFlush(any())).thenReturn(supplier);
        try {
            mvc.perform(MockMvcRequestBuilders.put("/api/v1/supplier/1")
            .content(data)
            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1l));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
