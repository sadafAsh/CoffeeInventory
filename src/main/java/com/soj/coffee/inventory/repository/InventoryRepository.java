package com.soj.coffee.inventory.repository;

import com.soj.coffee.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
Inventory findFirstByProductId(long productId);
}
