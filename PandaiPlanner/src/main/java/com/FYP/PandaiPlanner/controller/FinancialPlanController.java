package com.FYP.PandaiPlanner.controller;


import com.FYP.PandaiPlanner.dto.FinancialPlanDTO;
import com.FYP.PandaiPlanner.dto.TransactionDTO;
import com.FYP.PandaiPlanner.entity.FinancialPlan;
import com.FYP.PandaiPlanner.entity.Transaction;
import com.FYP.PandaiPlanner.service.FinancialPlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "api/v1/financialPlan")
public class FinancialPlanController {


    private final FinancialPlanService financialPlanService;

    public FinancialPlanController(FinancialPlanService financialPlanService) {
        this.financialPlanService = financialPlanService;
    }

    @GetMapping("/getFinancialPlanInfo")
    public ResponseEntity<?> getBudgetsForCurrentMonth(
            @RequestParam Long userId
    ) {
        try {
            List<FinancialPlan> financialPlans = financialPlanService.findFinancialPlan(userId);
            return ResponseEntity.ok(financialPlans);
        } catch (Exception e) {
            // Log the error for debugging purposes
            e.printStackTrace();
            // Return a more specific error message
            String errorMessage = "An error occurred while retrieving the financial plan: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }


    @DeleteMapping("/deleteFinancialPlan/{financialPlanId}")
    public ResponseEntity<String> deleteFinancialPlan(@PathVariable Long financialPlanId) {
        try {
            financialPlanService.deleteFinancial(financialPlanId);
            return new ResponseEntity<>("Financial plan with ID " + financialPlanId + " has been deleted successfully.", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createFinancialPlan")
    public ResponseEntity<?> createFinancialPlanning(@RequestBody FinancialPlanDTO financialPlanDTO) {
        try {
            financialPlanService.createFinancialPlan(financialPlanDTO);
            return ResponseEntity.ok().body("financialPlan successfully created.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error create financialPlan: " + e.getMessage());
        }
    }



}
