package com.soj.coffee.inventory.repository;

import com.soj.coffee.inventory.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier ,Long> {
}
