package com.soj.coffee.inventory.controller;

import com.soj.coffee.inventory.model.Transaction;
import com.soj.coffee.inventory.service.TransactionService;
import com.soj.coffee.inventory.util.InventoryRequest;
import com.soj.coffee.inventory.util.InventoryResponse;
import com.soj.coffee.inventory.util.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<Resource<Transaction>> findAll(){
        return transactionService.getAll();
    }

    @GetMapping("/{id}")
    public Resource<Transaction> getTransactionById(@PathVariable long id){
        return transactionService.getTransaction(id);
    }

    @PostMapping
    public Resource<InventoryResponse> addTransaction(@RequestBody InventoryRequest<Transaction> request){
        Transaction transaction=request.getAttribute();
        return transactionService.addTransaction(transaction);
    }
    @DeleteMapping("/{id}")
    public Resource<InventoryResponse> deleteTransaction(@PathVariable long id){
        return transactionService.deleteTransaction(id);
    }
    @PutMapping("/{id}")
    public Resource<InventoryResponse> updateTransaction(@PathVariable long id,@RequestBody InventoryRequest<Transaction> request){
        Transaction transaction=request.getAttribute();
        return transactionService.updateTransaction(id,transaction);
    }
}
