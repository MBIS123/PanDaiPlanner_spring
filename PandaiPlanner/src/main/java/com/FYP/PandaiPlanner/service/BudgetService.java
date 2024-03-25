package com.FYP.PandaiPlanner.service;

import com.FYP.PandaiPlanner.dto.BudgetDTO;
import com.FYP.PandaiPlanner.entity.Budget;
import com.FYP.PandaiPlanner.entity.User;
import com.FYP.PandaiPlanner.repository.BudgetRepository;
import com.FYP.PandaiPlanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BudgetService {
    private final BudgetRepository  budgetRepository;

    @Autowired
    public BudgetService(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public void createBudget(BudgetDTO budgetDTO) {
        Budget budget = new Budget();
        // Assuming you have a method to find the user by ID, and it's set in the DTO
        budget.setUser(budgetDTO.getUser()); // You might need to adjust this based on your user handling logic
        budget.setBudgetCategory(budgetDTO.getBudgetCategory());
        budget.setBudgetLimit(budgetDTO.getBudgetLimit());
        budget.setBudgetDate(budgetDTO.getBudgetDate());
        budget.setBudgetSpent(0);

        // Save the budget entity to the database
        budgetRepository.save(budget);
    }




}
