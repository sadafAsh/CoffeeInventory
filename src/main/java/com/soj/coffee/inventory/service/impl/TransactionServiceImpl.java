package com.soj.coffee.inventory.service.impl;

import com.soj.coffee.inventory.model.Transaction;
import com.soj.coffee.inventory.repository.TransactionRepository;
import com.soj.coffee.inventory.service.InventoryService;
import com.soj.coffee.inventory.service.TransactionService;
import com.soj.coffee.inventory.util.InventoryResponse;
import com.soj.coffee.inventory.util.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.soj.coffee.inventory.model.Transaction.OBJECT_TYPE;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private InventoryService inventoryService;

    @Override
    public List<Resource<Transaction>> getAll() {
        List<Resource<Transaction>> resources=new ArrayList<>();
        transactionRepository.findAll().forEach(x->{
            Resource<Transaction> resource=new Resource<>(x.getId(),OBJECT_TYPE,x);
            resources.add(resource);
        });
        return resources;
    }

    @Override
    public Resource<Transaction> getTransaction(long id) {
        Optional<Transaction> transaction=transactionRepository.findById(id);
       if (transaction.isPresent()) {
           return new Resource<>(transaction.get().getId(), OBJECT_TYPE, transaction.get());
       }else {
           throw new IllegalArgumentException(id+" is not present");
       }
    }

    @Override
    public Resource<InventoryResponse> addTransaction(Transaction transaction) {
        Transaction transaction1=transactionRepository.saveAndFlush(transaction);
        InventoryResponse response=new InventoryResponse(transaction1.getId(),"create successfully");
        return new Resource<>(transaction1.getId(),OBJECT_TYPE,response);

    }

    @Override
    public Resource<InventoryResponse> deleteTransaction(long id) {
       transactionRepository.deleteById(id);
       InventoryResponse response=new InventoryResponse(id,"delete successfully");
        return new Resource<>(id,OBJECT_TYPE,response);
    }

    @Override
    public Resource<InventoryResponse> updateTransaction(long id, Transaction transaction) {
     Optional< Transaction> existingTransaction=transactionRepository.findById(id) ;
        BeanUtils.copyProperties(transaction,existingTransaction,"transaction_id");
        if (existingTransaction.isPresent()){
        Transaction transaction1=transactionRepository.saveAndFlush(transaction);
        InventoryResponse response=new InventoryResponse(transaction1.getId(),"update successfully");
        return new Resource<>(transaction1.getId(),OBJECT_TYPE,response) ;
    }else {
            throw new IllegalArgumentException(id+" is not present");
        }
}
}
