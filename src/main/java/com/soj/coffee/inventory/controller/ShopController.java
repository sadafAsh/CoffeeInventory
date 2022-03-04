package com.soj.coffee.inventory.controller;

import com.soj.coffee.inventory.model.Shop;
import com.soj.coffee.inventory.service.ShopService;
import com.soj.coffee.inventory.util.InventoryRequest;
import com.soj.coffee.inventory.util.InventoryResponse;
import com.soj.coffee.inventory.util.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @GetMapping("/shop")
    public List<Resource<Shop>> findAllShop(){
       return shopService.getAll();
    }

    @GetMapping("/shop/{id}")
    public Resource<Shop> getShopById(@PathVariable long id) {
        return shopService.getShop(id);
    }

    @PostMapping("/shop")
    public Resource<InventoryResponse> addShop(@RequestBody InventoryRequest<Shop> request){
        Shop shop=request.getAttribute();
        return shopService.addShop(shop);
    }

    @DeleteMapping("/shop/{id}")
    public Resource<InventoryResponse> deleteShop(@PathVariable long id){
       return shopService.deleteShop(id);
    }

    @PutMapping("/shop/{id}")
    public Resource<InventoryResponse> updateShop(@PathVariable long id, @RequestBody InventoryRequest<Shop> request){
       Shop shop=request.getAttribute();
        return  shopService.updateShop(id,shop);
    }
}
