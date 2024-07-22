package com.innova.masraf_takip_api.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.innova.masraf_takip_api.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	  List<Transaction> findByUserId(Long userId);

	// for specific time 
    @Query("SELECT t.userId, SUM(t.amount) FROM Transaction t WHERE t.transactionDate = :date GROUP BY t.userId")
    List<Object[]> aggregateTransactionsByDate(@Param("date") LocalDate date);
    
    // for time intervals 
    @Query("SELECT t.userId, SUM(t.amount) FROM Transaction t WHERE t.transactionDate BETWEEN :startDate AND :endDate GROUP BY t.userId")
    List<Object[]> aggregateTransactionsBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
