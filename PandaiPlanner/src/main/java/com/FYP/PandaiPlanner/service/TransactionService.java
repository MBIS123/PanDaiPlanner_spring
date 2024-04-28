package com.FYP.PandaiPlanner.service;

import com.FYP.PandaiPlanner.dto.TransactionDTO;
import com.FYP.PandaiPlanner.entity.Budget;
import com.FYP.PandaiPlanner.entity.Transaction;
import com.FYP.PandaiPlanner.entity.User;
import com.FYP.PandaiPlanner.repository.BudgetRepository;
import com.FYP.PandaiPlanner.repository.TransactionRepository;
import com.FYP.PandaiPlanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;


    private BudgetRepository budgetRepository;


    private UserRepository userRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, BudgetRepository budgetRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.budgetRepository = budgetRepository;
        this.userRepository = userRepository;
    }
    public void createTransaction(TransactionDTO transactionDTO) {

        User user = userRepository.findById(transactionDTO.getUserId())
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + transactionDTO.getUserId()));

        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setNote(transactionDTO.getNote());
        transaction.setBudgetCategory(transactionDTO.getBudgetCategory());
        transaction.setTransactionDate(transactionDTO.getTransactionDate());
        transaction.setTransactionAmount(transactionDTO.getTransactionAmount());
        transaction.setTransactionTime(transactionDTO.getTransactionTime());
        transactionRepository.save(transaction);
    }

    public void updateTransaction(TransactionDTO transactionDTO) {
        Transaction existingTransaction = transactionRepository.findById(transactionDTO.getTransactionId())
                .orElseThrow(() -> new NoSuchElementException("Transaction not found with id: " + transactionDTO.getTransactionId()));

        LocalDate yearMonth = existingTransaction.getTransactionDate();
        Optional<Budget> optionalBudget = budgetRepository.findExistingBudgetByCategoryAndDate(existingTransaction.getUser().getId(), existingTransaction.getBudgetCategory(), yearMonth);

        if (optionalBudget.isPresent()) {
            Budget budget = optionalBudget.get();

            double existingTransactionAmount = existingTransaction.getTransactionAmount();
            double budgetSpent = budget.getBudgetSpent();

            budget.setBudgetSpent(budgetSpent - existingTransactionAmount + transactionDTO.getTransactionAmount());
            budgetRepository.save(budget);


        }
        existingTransaction.setNote(transactionDTO.getNote());
        existingTransaction.setBudgetCategory(transactionDTO.getBudgetCategory());
        existingTransaction.setTransactionDate(transactionDTO.getTransactionDate());
        existingTransaction.setTransactionAmount(transactionDTO.getTransactionAmount());
        existingTransaction.setTransactionTime(transactionDTO.getTransactionTime());
        transactionRepository.save(existingTransaction);
    }


    public void deleteTransaction(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new NoSuchElementException("Transaction not found with id: " + transactionId));

        LocalDate yearMonth = transaction.getTransactionDate();
        Optional<Budget> optionalBudget = budgetRepository.findExistingBudgetByCategoryAndDate(transaction.getUser().getId(), transaction.getBudgetCategory(), yearMonth);
        if (optionalBudget.isPresent()) {
            Budget budget = optionalBudget.get();
            double transactionAmount = transaction.getTransactionAmount();
            double budgetSpent = budget.getBudgetSpent();
            budget.setBudgetSpent(budgetSpent - transactionAmount);
            budgetRepository.save(budget);
        }
        transactionRepository.delete(transaction);
    }



    public List<Transaction>  getTransactionByDateAndId (Long userId, int year, int month){
        LocalDate searchDate = LocalDate.of(year, month, 1);
       return transactionRepository.findTransactionByUserIDAndDate( userId, searchDate );
    }


    public List<Transaction> getTransactionSummaryByDateAndIdOrderByBudgetCategory(Long userId, int year, int month) {
        LocalDate searchDate = LocalDate.of(year, month, 1);
        List<Transaction> transactionList = transactionRepository.findTransactionByUserIDAndDate(userId, searchDate);

        Map<String, Double> categorySumMap = new HashMap<>();

        for (Transaction transaction : transactionList) {
            String budgetCategory = transaction.getBudgetCategory();
            double transactionAmount = transaction.getTransactionAmount();

            categorySumMap.put(budgetCategory, categorySumMap.getOrDefault(budgetCategory, 0.0) + transactionAmount);
        }

        List<Transaction> summaryList = new ArrayList<>();
        for (Map.Entry<String, Double> entry : categorySumMap.entrySet()) {
            String budgetCategory = entry.getKey();
            double totalAmount = entry.getValue();

            Transaction summaryTransaction = new Transaction();
            summaryTransaction.setBudgetCategory(budgetCategory);
            summaryTransaction.setTransactionAmount(totalAmount);

            summaryList.add(summaryTransaction);
        }

        return summaryList;
    }



}
