package com.soj.coffee.inventory.service.impl;

import com.soj.coffee.inventory.model.Product;
import com.soj.coffee.inventory.repository.ProductRepository;
import com.soj.coffee.inventory.service.ProductService;
import com.soj.coffee.inventory.util.InventoryResponse;
import com.soj.coffee.inventory.util.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.soj.coffee.inventory.model.Product.OBJECT_TYPE;

@Service
public class ProductServiceImpl implements ProductService {
  @Autowired
  private ProductRepository productRepository;
    @Override
    public List<Resource<Product>> findAll() {
        List<Resource<Product>> resources=new ArrayList<>();
        List<Product> products=productRepository.findAll();
        products.forEach(x->{
            Resource<Product> resource=new Resource<>(x.getId(),OBJECT_TYPE,x);
            resources.add(resource);
        });
         return resources;

    }

    @Override
    public Resource<Product> getProductById(long id) {
       Optional<Product> product= productRepository.findById(id);
       if (product.isPresent()) {
           return new Resource<>(product.get().getId(), OBJECT_TYPE, product.get());
       }else {
           throw new IllegalArgumentException(id +" is not present");
       }
    }

    @Override
    public Resource addProduct(Product product) {
        Product product1=productRepository.saveAndFlush(product);
        InventoryResponse response=new InventoryResponse(product1.getId(),"create successfully");
       return new Resource<>(product.getId(),OBJECT_TYPE,response);

    }

    @Override
    public Resource<InventoryResponse> deleteProduct(long id) {
productRepository.deleteById(id);
InventoryResponse response=new InventoryResponse(id,"delete successfully");
return new Resource<>(id,OBJECT_TYPE,response);

    }

    @Override
    public Resource<InventoryResponse> updateProduct(Long id, Product product) {
Optional<Product> existingProduct=productRepository.findById(id);
BeanUtils.copyProperties(product,existingProduct,"product_id");
if (existingProduct.isPresent()){
Product product1=productRepository.saveAndFlush(product);
InventoryResponse response=new InventoryResponse(product1.getId(),"update successfully");
return new Resource<>(product1.getId(),OBJECT_TYPE,response);
}else {
    throw new IllegalArgumentException(id +" is not present");
}
    }
}
