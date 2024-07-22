package com.innova.masraf_takip_api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.innova.masraf_takip_api.model.Transaction;
import com.innova.masraf_takip_api.service.TransactionService;

@RestController
@RequestMapping("/costs")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Get all transactions
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }
    
    // Create a new transaction
    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }

    // Get a transaction by id
    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable("id") Long id) {
        return transactionService.getTransactionById(id);
    }

    // Update transaction by using id
    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable("id") Long id, @RequestBody Transaction transactionDetails) {
        return transactionService.updateTransaction(id, transactionDetails);
    }

    // Delete transaction by using id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable("id") Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.ok().build();
    }
    
    // Get total transactions by using user id for given user
    @GetMapping("/total/{userId}")
    public Double getTotalTransactionByUserId(@PathVariable("userId") Long userId) {
        return transactionService.getTotalTransactionByUserId(userId);
    }
}
