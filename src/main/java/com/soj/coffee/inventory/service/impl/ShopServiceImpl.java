package com.soj.coffee.inventory.service.impl;

import com.soj.coffee.inventory.model.Shop;
import com.soj.coffee.inventory.repository.ShopRepository;
import com.soj.coffee.inventory.service.ShopService;
import com.soj.coffee.inventory.util.InventoryResponse;
import com.soj.coffee.inventory.util.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.soj.coffee.inventory.model.Shop.OBJECT_TYPE;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopRepository shopRepository;

    @Override
    public List<Resource<Shop>> getAll() {
        List<Resource<Shop>> resources=new ArrayList<>();
         shopRepository.findAll().forEach(x->{
             Resource<Shop> resource=new Resource<>(x.getId(),OBJECT_TYPE,x);
             resources.add(resource);
         });
         return resources;
    }

    @Override
    public Resource<Shop> getShop(long id) {
        Shop shop= shopRepository.findById(id).get();
        return new Resource<>(shop.getId(),OBJECT_TYPE, shop);

    }

    @Override
    public Resource<InventoryResponse> addShop(Shop shop) {
        Shop shop1= shopRepository.saveAndFlush(shop);
        InventoryResponse response=new InventoryResponse(shop1.getId(),"create successfully");
        return new Resource<>(shop1.getId(),OBJECT_TYPE,response);

    }

    @Override
    public Resource<InventoryResponse> deleteShop(long id) {
        shopRepository.deleteById(id);
        InventoryResponse response=new InventoryResponse(id,"delete successfully");
        return new Resource<>(id,OBJECT_TYPE,response);


    }

    @Override
    public Resource<InventoryResponse> updateShop(long id, Shop shop) {
        Shop existingShop=shopRepository.getById(id);
        BeanUtils.copyProperties(shop,existingShop,"shop_id");
        Shop shop1= shopRepository.saveAndFlush(existingShop);
    InventoryResponse response=new InventoryResponse(shop1.getId(),"update successfully");
    return new Resource<>(id,OBJECT_TYPE,response);
    }
}
