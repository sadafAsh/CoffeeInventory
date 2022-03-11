package com.soj.coffee.inventory.service.impl;

import com.soj.coffee.inventory.model.Transaction;
import com.soj.coffee.inventory.repository.TransactionRepository;
import com.soj.coffee.inventory.service.TransactionService;
import com.soj.coffee.inventory.util.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
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
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc

class TransactionServiceImplTest {


        @Autowired
        private TransactionService service;
        @MockBean
        private TransactionRepository repository;

        @Test
        void testToGetAllTransaction() {
            Transaction transaction=new Transaction();
            transaction.setId(1l);
            List<Transaction> transactions=new ArrayList<>();
            transactions.add(transaction);
            List<Resource<Transaction>> list=new ArrayList<>();
            Resource<Transaction> resources=new Resource<>(1l,OBJECT_TYPE,transaction);
            when(repository.findAll()).thenReturn(transactions);
            List<Resource<Transaction>>  resource=service.getAll();
            list.add(resources);
            Assertions.assertEquals(1,transactions.size());
            Assertions.assertEquals(1,list.size());
        }

        @Test
        void testToGetTransactionById() {
           Transaction transaction= new Transaction();
           transaction.setId(1l);
            when(repository.findById(1l)).thenReturn(Optional.of(transaction));
            Resource resource=service.getTransaction(1l);
            Assertions.assertEquals(1l,resource.getId());
        }

        @Test
        void testToAddTransaction() {
            Transaction transaction=new Transaction();
            transaction.setId(1l);
            when(repository.saveAndFlush(any())).thenReturn(transaction);
            Resource resource1=service.addTransaction(transaction);
            Assertions.assertEquals(1l,resource1.getId());
        }

        @Test
        void testToDeleteTransactionById() {
            Transaction transaction=new Transaction();
            transaction.setId(1l);
            doNothing().when(repository).deleteById(1l);
            Resource resource1=service.deleteTransaction(1l);
            Assertions.assertEquals(1l,resource1.getId());
        }

        @Test
        void testToUpdateTransactionById() {
            Transaction transaction=new Transaction();
            transaction.setId(1l);
            when(repository.findById(1l)).thenReturn(java.util.Optional.of(transaction));
            when(repository.saveAndFlush(any())).thenReturn(transaction);
            Resource resource1=service.updateTransaction(1l,transaction);
            verify(repository,times(1)).saveAndFlush(transaction);

        }
    @Test
    void testToGetExceptionTransactionById(){
            Throwable exception=Assertions.assertThrows(IllegalArgumentException.class, new Executable() {
                @Override
                public void execute() throws Throwable {
                    Transaction transaction = new Transaction();
                    transaction.setId(1l);
                    when(repository.findById(2l)).thenReturn(Optional.of(transaction));
                    Resource resource1 = service.getTransaction(1l);
                }
            });
            Assertions.assertEquals("1 is not present",exception.getMessage());
    }
    @Test
    void testForUpdatingShopByGettingException(){
        Throwable exception=Assertions.assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Transaction transaction = new Transaction();
                transaction.setId(1l);
                when(repository.findById(2l)).thenReturn(Optional.of(transaction));
                when(repository.saveAndFlush(any())).thenReturn(transaction);
                Resource resource1 = service.updateTransaction(1l, transaction);
            }
        });
        Assertions.assertEquals("1 is not present",exception.getMessage());

    }

}