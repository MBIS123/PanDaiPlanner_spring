package com.FYP.PandaiPlanner.service;

import com.FYP.PandaiPlanner.dto.TransactionDTO;
import com.FYP.PandaiPlanner.entity.Transaction;
import com.FYP.PandaiPlanner.entity.User;
import com.FYP.PandaiPlanner.repository.TransactionRepository;
import com.FYP.PandaiPlanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;

    private UserRepository userRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;

        this.userRepository = userRepository;
    }

    public void createTransaction(TransactionDTO transactionDTO) {

        User user = userRepository.findById(transactionDTO.getUserId())
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + transactionDTO.getUserId()));

        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setNote(transactionDTO.getNote());
        transaction.setBudgetCategory(transactionDTO.getBudgetCategory());
        transaction.setTransactionDate(transactionDTO.getTransactionDate()); // Assuming the date is a String and needs to be parsed
        transaction.setTransactionAmount(transactionDTO.getTransactionAmount());
        transaction.setTransactionTime(transactionDTO.getTransactionTime());
        transactionRepository.save(transaction);
    }

    public void updateTransaction(TransactionDTO transactionDTO) {

        Transaction transaction = transactionRepository.findById(transactionDTO.getTransactionId())
                .orElseThrow(() -> new NoSuchElementException("Transaction not found with id: " + transactionDTO.getTransactionId()));

        transaction.setNote(transactionDTO.getNote());
        transaction.setBudgetCategory(transactionDTO.getBudgetCategory());
        transaction.setTransactionDate(transactionDTO.getTransactionDate()); // Assuming the date is a String and needs to be parsed
        transaction.setTransactionAmount(transactionDTO.getTransactionAmount());
        transaction.setTransactionTime(transactionDTO.getTransactionTime());

        transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long transactionId) {
        // Check if the transaction exists
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new NoSuchElementException("Transaction not found with id: " + transactionId));

        // Delete the transaction
        transactionRepository.delete(transaction);
    }


    public List<Transaction>  getTransactionByDateAndId (Long userId, int year, int month){
        LocalDate searchDate = LocalDate.of(year, month, 1); // Set day to 1 for the first day of the month
       return transactionRepository.findTransactionByUserIDAndDate( userId, searchDate );
    }



}
