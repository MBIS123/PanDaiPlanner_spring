package com.FYP.PandaiPlanner.service;

import com.FYP.PandaiPlanner.dto.TransactionDTO;
import com.FYP.PandaiPlanner.entity.Transaction;
import com.FYP.PandaiPlanner.entity.User;
import com.FYP.PandaiPlanner.repository.TransactionRepository;
import com.FYP.PandaiPlanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        transaction.setBudgetCategory(transactionDTO.getBudgetCategory());
        transaction.setNote(transactionDTO.getNote());
        transaction.setTransactionDate(transactionDTO.getTransactionDate()); // Assuming the date is a String and needs to be parsed
        transactionRepository.save(transaction);

    }


}
