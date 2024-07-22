package com.innova.masraf_takip_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.innova.masraf_takip_api.model.Transaction;
import com.innova.masraf_takip_api.repository.TransactionRepository;
import com.innova.masraf_takip_api.repository.UserRepository;

import org.springframework.transaction.annotation.Transactional;

import com.innova.masraf_takip_api.exception.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Transaction createTransaction(Transaction transaction) {
        // Kullanıcı kimliğinin geçerli olup olmadığını kontrol et
       userRepository.findById(transaction.getUserId())
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + transaction.getUserId()));
        
        return transactionRepository.save(transaction);
    }
    @Transactional(readOnly = true)
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id " + id));
    }
    
    @Transactional
    public Transaction updateTransaction(Long id, Transaction transactionDetails) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id " + id));
        transaction.setUserId(transactionDetails.getUserId());
        transaction.setAmount(transactionDetails.getAmount());
        transaction.setDescription(transactionDetails.getDescription());
        transaction.setTransactionDate(transactionDetails.getTransactionDate());
        
        // Kullanıcı kimliğinin geçerli olup olmadığını kontrol et
        userRepository.findById(transaction.getUserId())
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + transaction.getUserId()));
        
        return transactionRepository.save(transaction);
    }
    @Transactional
    public void deleteTransaction(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id " + id));
        transactionRepository.delete(transaction);
    }
    @Transactional(readOnly = true)
    public Double getTotalTransactionByUserId(Long userId) {
        List<Transaction> transactions = transactionRepository.findByUserId(userId);
        return transactions.stream().mapToDouble(t -> t.getAmount().doubleValue()).sum();
    }
    @Transactional(readOnly = true)
    public List<Object[]> aggregateTransactionsByDate(String date) {
        return transactionRepository.aggregateTransactionsByDate(LocalDate.parse(date));
    }
    @Transactional(readOnly = true)
    public List<Object[]> aggregateTransactionsByWeek(String date) {
        LocalDate startDate = LocalDate.parse(date);
        LocalDate endDate = startDate.plusWeeks(1);
        return transactionRepository.aggregateTransactionsBetweenDates(startDate, endDate);
    }
    @Transactional(readOnly = true)
    public List<Object[]> aggregateTransactionsByMonth(String date) {
        LocalDate startDate = LocalDate.parse(date);
        LocalDate endDate = startDate.plusMonths(1);
        return transactionRepository.aggregateTransactionsBetweenDates(startDate, endDate);
    }
}
