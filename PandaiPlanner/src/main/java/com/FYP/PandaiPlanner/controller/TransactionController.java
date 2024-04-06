package com.FYP.PandaiPlanner.controller;

import com.FYP.PandaiPlanner.dto.BudgetDTO;
import com.FYP.PandaiPlanner.dto.TransactionDTO;
import com.FYP.PandaiPlanner.service.BudgetService;
import com.FYP.PandaiPlanner.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class TransactionController {


    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/createTransaction")
    public ResponseEntity<?> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        try {
            transactionService.createTransaction(transactionDTO);
            return ResponseEntity.ok().body("transaction successfully created.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error create transcation: " + e.getMessage());
        }
    }





}
