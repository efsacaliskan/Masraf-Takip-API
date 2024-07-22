package com.innova.masraf_takip_api.scheduler;

import com.innova.masraf_takip_api.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class TransactionScheduler {

    @Autowired
    private TransactionService transactionService;

    @Scheduled(cron = "0 0 0 * * ?")  // Her gün gece 00:00'da 
    public void aggregateDailyTransactions() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = yesterday.format(formatter);
        List<Object[]> results = transactionService.aggregateTransactionsByDate(date);
        logResults("Daily", results);
    }

    @Scheduled(cron = "0 0 0 * * MON")  // Her hafta Pazartesi 00:00'da 
    public void aggregateWeeklyTransactions() {
        LocalDate lastWeek = LocalDate.now().minusWeeks(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = lastWeek.format(formatter);
        List<Object[]> results = transactionService.aggregateTransactionsByWeek(date);
        logResults("Weekly", results);
    }

    @Scheduled(cron = "0 0 0 1 * ?")  // Her ayın 1'inde 00:00'da  
    public void aggregateMonthlyTransactions() {
        LocalDate lastMonth = LocalDate.now().minusMonths(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = lastMonth.format(formatter);
        List<Object[]> results = transactionService.aggregateTransactionsByMonth(date);
        logResults("Monthly", results);
    }

    private void logResults(String period, List<Object[]> results) {
        System.out.println(period + " Aggregation Results:");
        for (Object[] result : results) {
            System.out.println("UserId: " + result[0] + ", Total Amount: " + result[1]);
        }
    }
}
