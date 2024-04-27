package com.FYP.PandaiPlanner.controller;

import com.FYP.PandaiPlanner.dto.IncomeDTO;
import com.FYP.PandaiPlanner.entity.Income;
import com.FYP.PandaiPlanner.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/income")
public class IncomeController {

    private final IncomeService incomeService;

    @Autowired
    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @PostMapping("/createIncome")
    public ResponseEntity<?> createOrUpdateIncome(@RequestBody IncomeDTO incomeDTO) {
        try {
            incomeService.createOrUpdateIncome(incomeDTO);
            return ResponseEntity.ok().body("Income successfully updated.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating income: " + e.getMessage());
        }
    }



    @GetMapping("/getAllIncome")
    public ResponseEntity<?> getAllIncome( @RequestParam Long userId) {
        try {
            List<IncomeDTO> incomeList = incomeService.findAllIncome(userId);
            return ResponseEntity.ok(incomeList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while retrieving the income list");
        }
    }


}
