package com.soj.coffee.inventory.repository;

import com.soj.coffee.inventory.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop,Long> {
}
