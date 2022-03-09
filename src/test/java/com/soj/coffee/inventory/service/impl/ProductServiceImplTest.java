package com.soj.coffee.inventory.service.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.soj.coffee.inventory.model.Product;
import com.soj.coffee.inventory.model.Supplier;
import com.soj.coffee.inventory.repository.ProductRepository;
import com.soj.coffee.inventory.service.ProductService;
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

import static com.soj.coffee.inventory.model.Product.OBJECT_TYPE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ProductServiceImplTest {
    @Autowired
    private ProductService service;
    @MockBean
    private ProductRepository repository;

    @Test
    void testToGetAllProduct() {
        Product product=new Product();
        product.setId(1l);
        List<Product> products=new ArrayList<>();
        products.add(product);
        Resource<Product> resources=new Resource<>(1l,OBJECT_TYPE,product);
        when(repository.findAll()).thenReturn(products);
        List<Resource<Product>>  resource=service.findAll();
        resource.add(resources);
        Assertions.assertEquals(1,products.size());
        Assertions.assertEquals(2,resource.size());

    }

    @Test
    void testToGetProductById() {
        Product product=new Product();
        product.setId(1l);
        when(repository.findById(1l)).thenReturn(java.util.Optional.of(product));
        Resource resource=service.getProductById(1l);
        Assertions.assertEquals(1l,resource.getId());
    }

    @Test
    void testToAddProduct() {
        Product product=new Product();
        product.setId(1l);
        when(repository.saveAndFlush(any())).thenReturn(product);
        Resource resource1=service.addProduct(product);
        Assertions.assertEquals(1l,resource1.getId());
    }

    @Test
    void testToDeleteProdutById() {
        Product product=new Product();
        product.setId(1l);
        doNothing().when(repository).deleteById(1l);
        Resource resource1=service.deleteProduct(1l);
        Assertions.assertEquals(1l,resource1.getId());
    }

    @Test
    void testToUpdateProductById() {
        Product product=new Product();
        product.setId(1l);
        when(repository.findById(1l)).thenReturn(java.util.Optional.of(product));
        when(repository.saveAndFlush(any())).thenReturn(product);
        Resource resource1=service.updateProduct(1l,product);
        verify(repository,times(1)).saveAndFlush(product);

    }

}