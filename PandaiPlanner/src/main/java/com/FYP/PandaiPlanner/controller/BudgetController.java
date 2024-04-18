package com.FYP.PandaiPlanner.controller;
import com.FYP.PandaiPlanner.dto.BudgetDTO;
import com.FYP.PandaiPlanner.dto.UserDTO;
import com.FYP.PandaiPlanner.entity.Budget;
import com.FYP.PandaiPlanner.entity.User;
import com.FYP.PandaiPlanner.service.BudgetService;
import com.FYP.PandaiPlanner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "api/v1/budget")
public class BudgetController {

    private final BudgetService budgetService;

    @Autowired
    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PostMapping("/createBudget")
    public ResponseEntity<?> createOrUpdateBudget(@RequestBody BudgetDTO budgetDTO) {
        try {
            budgetService.createOrUpdateBudget(budgetDTO);
            return ResponseEntity.ok().body("Budget successfully updated.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating budget: " + e.getMessage());
        }
    }

    @PostMapping("/recordBudgetSpent")
    public ResponseEntity<?> recordBudgetSpent(@RequestBody BudgetDTO budgetDTO) {
        try {
            budgetService.recordBudgetSpent(budgetDTO);
            return ResponseEntity.ok().body("Budget spent updated.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating budget: " + e.getMessage());
        }
    }

//    @GetMapping("/limit")
//    public ResponseEntity<?> getBudgetLimit(@RequestParam Long userId, @RequestParam String budgetCategory) {
//        try {
//
//            System.out.println("from controller  The userid is:"+userId + "the budgetCategory is"+budgetCategory);
//            Integer budgetLimit = budgetService.findBudgetLimitByUserIdAndCategory(userId, budgetCategory);
//
//            return ResponseEntity.ok(budgetLimit);
//        } catch (NoSuchElementException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Budget not found for the provided user ID and category");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while retrieving the budget limit");
//        }
//    }

    @GetMapping("/budgetCurrentMonth")
    public ResponseEntity<?> getBudgetsForCurrentMonth(@RequestParam Long userId ) {
        try {
            List<Budget> budgets = budgetService.findCurrentMonthBudgets(userId);
            return ResponseEntity.ok(budgets);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while retrieving the budgets");
        }
    }


}
