package com.soj.coffee.inventory.service;

import com.soj.coffee.inventory.model.Transaction;
import com.soj.coffee.inventory.util.InventoryResponse;
import com.soj.coffee.inventory.util.Resource;

import java.util.List;

public interface TransactionService {
    public List<Resource<Transaction>> getAll();
    public Resource<Transaction> getTransaction(long id);
    public Resource<InventoryResponse> addTransaction(Transaction transaction);
    public Resource<InventoryResponse> deleteTransaction(long id);
    public Resource<InventoryResponse> updateTransaction(long id,Transaction transaction);
}
