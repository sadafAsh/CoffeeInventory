package com.soj.coffee.inventory.controller;


import com.soj.coffee.inventory.model.Transaction;
import com.soj.coffee.inventory.service.TransactionService;
import com.soj.coffee.inventory.util.InventoryRequest;
import com.soj.coffee.inventory.util.InventoryResponse;
import com.soj.coffee.inventory.util.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.soj.coffee.inventory.model.Transaction.OBJECT_TYPE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TransactionControllerTest {

    @Autowired
    private TransactionController controller;

    @MockBean
    private TransactionService service;

    @Test
    void testToFindAllTransactionFromDb() {
        Transaction transaction=new Transaction();
        List<Resource<Transaction>> resources = new ArrayList<>();
        InventoryRequest<Transaction> request = new InventoryRequest<>(1l, OBJECT_TYPE, transaction);
        Resource<Transaction> resource = new Resource(1l, OBJECT_TYPE, request);
        when(service.getAll()).thenReturn(resources);
        List<Resource<Transaction>> resource1 = controller.findAll();
        resources.add(resource);
        Assertions.assertEquals(1, resources.size());
        Assertions.assertEquals(resources, resource1);

    }

    @Test
    void testToGetTransactionByIdFromDb() {
        Transaction transaction= new Transaction();
        Resource<Transaction> resource = new Resource<>(1l, OBJECT_TYPE, transaction);
        resource.setAttribute(transaction);
        when(service.getTransaction(1l)).thenReturn(resource);
        Resource resource1 = controller.getTransactionById(1l);
        Assertions.assertEquals(1l, resource1.getId());
        Assertions.assertEquals(transaction, resource1.getAttribute());

    }

    @Test()
    void testToAddTransactionInDb() {
        Transaction transaction=new Transaction();
        InventoryRequest<Transaction> request = new InventoryRequest<>(1l, OBJECT_TYPE, transaction);
        Resource resource = new Resource<>(1l, OBJECT_TYPE, request);
        when(service.addTransaction(any())).thenReturn(resource);
        Resource resource1 = controller.addTransaction(request);
        Assertions.assertEquals(resource1, resource);
        Assertions.assertEquals( "Transaction",resource1.getType());

    }

    @Test
    void testToDeleteTransactionByIdFromDb() {
        Transaction transaction=new Transaction();
        InventoryRequest<Transaction> request = new InventoryRequest<>(1l, OBJECT_TYPE, transaction);
        Resource<InventoryResponse> resource = new Resource(1l, OBJECT_TYPE, request);
        when(service.deleteTransaction(1l)).thenReturn(resource);
        Resource resource1 = controller.deleteTransaction(1l);
        Assertions.assertEquals(1l, resource1.getId());
        verify(service, times(1)).deleteTransaction(1l);
    }

    @Test
    void testToUpdateTransactionByIdFromDb() {
        Transaction transaction=new Transaction();
        InventoryRequest<Transaction> request = new InventoryRequest<>(1l, OBJECT_TYPE, transaction);
        InventoryResponse response = new InventoryResponse(1l, "update successful");
        Resource<InventoryResponse> resource = new Resource<>(1l, OBJECT_TYPE, response);
        when(service.updateTransaction(anyLong(), any())).thenReturn(resource);
        Resource resource1 = controller.updateTransaction(1l, request);
        Assertions.assertEquals(resource1, resource);
    }
}