package com.soj.coffee.inventory.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TransactionTest {

    @Test
    void testToGetTransactionId() {
        Transaction transaction=new Transaction();
        transaction.setId(1l);
        Assertions.assertEquals(1,transaction.getId());
    }

    @Test
    void getQuantity() {
        Transaction transaction=new Transaction();
        transaction.setQuantity(2);
        Assertions.assertEquals(2,transaction.getQuantity());
    }

    @Test
    void getShop() {
        Transaction transaction=new Transaction();
        Shop shop=new Shop();
        transaction.setShop(shop);
        Assertions.assertEquals(shop,transaction.getShop());
    }

    @Test
    void getSupplier() {
        Transaction transaction=new Transaction();
        Supplier supplier=new Supplier();
        transaction.setSupplier(supplier);
        Assertions.assertEquals(supplier,transaction.getSupplier());
    }

    @Test
    void getProduct() {
        Transaction transaction=new Transaction();
        Product product=new Product();
        transaction.setProduct(product);
        Assertions.assertEquals(product,transaction.getProduct());
    }

    @Test
    void getDate(){
        Transaction transaction=new Transaction();
        Date date=new Date();
        transaction.setDate(date);
        Assertions.assertEquals(date,transaction.getDate());
    }
}