package com.soj.coffee.inventory.controller;

import com.soj.coffee.inventory.model.Product;
import com.soj.coffee.inventory.service.ProductService;
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

import static com.soj.coffee.inventory.model.Product.OBJECT_TYPE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private ProductController controller;

    @MockBean
    private ProductService service;


    @Test
    void testToFindAllProductFromDb() {
        Product product=new Product();
        List<Resource<Product>> resources = new ArrayList<>();
        InventoryRequest<Product> request = new InventoryRequest<>(1l, OBJECT_TYPE, product);
        Resource<Product> resource = new Resource(1l, OBJECT_TYPE, request);
        when(service.findAll()).thenReturn(resources);
        List<Resource<Product>> resource1 = controller.getAll();
        resources.add(resource);
        Assertions.assertEquals(1, resources.size());
        Assertions.assertEquals(resources, resource1);

    }

    @Test
    void testToGetProductByIdFromDb() {
        Product product=new Product();
        product.setId(1l);
        product.setName("brew coffee");
        Resource<Product> resource = new Resource<>(1l, OBJECT_TYPE, product);
        resource.setAttribute(product);
        when(service.getProductById(1l)).thenReturn(resource);
        Resource resource1 = controller.getProductById(1l);
        Assertions.assertEquals(1l, resource1.getId());
        Assertions.assertEquals(product, resource1.getAttribute());

    }

    @Test()
    void testToAddProductInDb() {
        Product product=new Product();
        InventoryRequest<Product> request = new InventoryRequest<>(1l, OBJECT_TYPE, product);
        Resource resource = new Resource<>(1l, OBJECT_TYPE, request);
        when(service.addProduct(any())).thenReturn(resource);
        Resource resource1 = controller.addProduct(request);
        Assertions.assertEquals(resource1, resource);
        Assertions.assertEquals( "Product",resource1.getType());

    }

    @Test
    void testToDeleteProductByIdFromDb() {
        Product product=new Product();
        InventoryRequest<Product> request = new InventoryRequest<>(1l, OBJECT_TYPE, product);
        Resource<InventoryResponse> resource = new Resource(1l, OBJECT_TYPE, request);
        when(service.deleteProduct(1l)).thenReturn(resource);
        Resource resource1 = controller.deleteProductById(1l);
        Assertions.assertEquals(1l, resource1.getId());
        verify(service, times(1)).deleteProduct(1l);
    }

    @Test
    void testToUpdateProductByIdFromDb() {
        Product product=new Product();
        InventoryRequest<Product> request = new InventoryRequest<>(1l, OBJECT_TYPE, product);
        InventoryResponse response = new InventoryResponse(1l, "update successful");
        Resource<InventoryResponse> resource = new Resource<>(1l, OBJECT_TYPE, response);
        when(service.updateProduct(anyLong(), any())).thenReturn(resource);
        Resource resource1 = controller.updateProductById(1l, request);
        Assertions.assertEquals(resource1, resource);
    }
}