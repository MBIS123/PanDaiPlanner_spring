package com.FYP.PandaiPlanner.controller;

import com.FYP.PandaiPlanner.dto.BudgetDTO;
import com.FYP.PandaiPlanner.dto.TransactionDTO;
import com.FYP.PandaiPlanner.entity.Budget;
import com.FYP.PandaiPlanner.entity.Transaction;
import com.FYP.PandaiPlanner.service.BudgetService;
import com.FYP.PandaiPlanner.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/transaction")
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

    @PutMapping("/updateTransaction")
    public ResponseEntity<?> updateTransaction(@RequestBody TransactionDTO transactionDTO) {
        try {
            transactionService.updateTransaction(transactionDTO);
            return ResponseEntity.ok().body("transaction successfully updated.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updated transcation: " + e.getMessage());
        }
    }

    @DeleteMapping("/deleteTransaction/{transactionId}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long transactionId) {
        try {
            transactionService.deleteTransaction(transactionId);
            return ResponseEntity.ok().body("Transaction successfully deleted.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting transaction: " + e.getMessage());
        }
    }

    @GetMapping("/getTransactionInfo")
    public ResponseEntity<?> getBudgetsForCurrentMonth(
            @RequestParam Long userId,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month
    ) {
        try {
            // If year and month are not provided, default to current year and month
            if (year == null || month == null) {
                YearMonth currentYearMonth = YearMonth.now();
                year = currentYearMonth.getYear();
                month = currentYearMonth.getMonthValue();
            }

            List<Transaction> transactions = transactionService.getTransactionByDateAndId(userId, year, month);
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            // Log the error for debugging purposes
            e.printStackTrace();

            // Return a more specific error message
            String errorMessage = "An error occurred while retrieving the transaction: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }




}
