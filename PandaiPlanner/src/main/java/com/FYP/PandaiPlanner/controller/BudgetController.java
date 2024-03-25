package com.FYP.PandaiPlanner.controller;
import com.FYP.PandaiPlanner.dto.BudgetDTO;
import com.FYP.PandaiPlanner.dto.UserDTO;
import com.FYP.PandaiPlanner.entity.User;
import com.FYP.PandaiPlanner.service.BudgetService;
import com.FYP.PandaiPlanner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            budgetService.createBudget(budgetDTO);
            return ResponseEntity.ok().body("Budget successfully updated.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating budget: " + e.getMessage());
        }
    }
}
