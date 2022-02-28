package com.soj.coffee.inventory.repository;

import com.soj.coffee.inventory.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
