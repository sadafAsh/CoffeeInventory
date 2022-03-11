package com.soj.coffee.inventory.controller;

import com.soj.coffee.inventory.model.Product;
import com.soj.coffee.inventory.service.ProductService;
import com.soj.coffee.inventory.util.InventoryRequest;
import com.soj.coffee.inventory.util.InventoryResponse;
import com.soj.coffee.inventory.util.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Resource<Product>> getAll(){
        return productService.findAll();
    }

    @GetMapping("{id}")
    public Resource<Product> getProductById(@PathVariable Long id){
        return productService.getProductById(id);

    }
    @PostMapping
    public Resource<InventoryResponse> addProduct(@RequestBody InventoryRequest<Product> request){
     Product product=request.getAttribute();
      return  productService.addProduct(product);
    }

    @DeleteMapping("{id}")
    public Resource<InventoryResponse> deleteProductById(@PathVariable long id){
      return   productService.deleteProduct(id);
    }
@PutMapping("{id}")
    public Resource<InventoryResponse> updateProductById(@PathVariable long id, @RequestBody InventoryRequest<Product> request){
        Product product=request.getAttribute();
        return productService.updateProduct(id,product);
    }
}
