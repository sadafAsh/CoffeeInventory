package com.soj.coffee.inventory.integration;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soj.coffee.inventory.model.Shop;
import com.soj.coffee.inventory.model.Transaction;
import com.soj.coffee.inventory.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.support.TransactionTemplate;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
 class TransactionIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TransactionRepository repository;

    @Test
    void testToGetTransactionById() throws Exception {
        Transaction transaction=new Transaction();
        transaction.setId(1l);
        Shop shop=new Shop();
        shop.setId(1l);
        transaction.setShop(shop);
        given(repository.findById(1l)).willReturn(java.util.Optional.of(transaction));
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/transaction/1")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1l))
                .andExpect(MockMvcResultMatchers.jsonPath("$.attribute.shop.id").value(1l));

    }
    @Test
    void testToFindAllTheTransaction() throws Exception {
        Transaction transaction=new Transaction();
        transaction.setId(1l);
        List<Transaction> transactions=new ArrayList<>();
        transactions.add(transaction);
        given(repository.findAll()).willReturn(transactions);
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/transaction")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1l));
    }
    @Test
    void testToDeleteTheTransaction() throws Exception {
        Transaction transaction=new Transaction();
        transaction.setId(1l);
        doNothing().when(repository).deleteById(1l);
        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/transaction/1")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1l));
    }

@Test
    void testToAddANewTransaction() throws Exception {
    Transaction transaction=new Transaction();
    transaction.setId(1l);
    ObjectMapper object=new ObjectMapper();
    String jsonStr=object.writeValueAsString(transaction);
    given(repository.saveAndFlush(any())).willReturn(transaction);
    mvc.perform(MockMvcRequestBuilders.post("/api/v1/transaction")
    .contentType(MediaType.APPLICATION_JSON)
    .content(jsonStr))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1l));
}
//@Test
//    void testToUpdateTheExistingTransaction() throws Exception {
//        Transaction transaction=new Transaction();
//        transaction.setId(1l);
////    Stream<String> files= Files.lines(Paths.get("/src/test/resources/transaction/transaction.json"));
////    String data=files.collect(Collectors.joining("\n"));
//    ObjectMapper objectMapper=new ObjectMapper();
//    String data=objectMapper.writeValueAsString(transaction);
//    given(repository.findById(1l)).willReturn(java.util.Optional.of(transaction));
//    given(repository.saveAndFlush(any())).willReturn(transaction);
//    mvc.perform(MockMvcRequestBuilders.put("/api/v1/transaction/1")
//    .content(data)
//    .contentType(MediaType.APPLICATION_JSON))
//            .andExpect(status().isOk())
//            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1l));
//}
}

