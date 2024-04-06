package com.FYP.PandaiPlanner.service;

import com.FYP.PandaiPlanner.dto.BudgetDTO;
import com.FYP.PandaiPlanner.entity.Budget;
import com.FYP.PandaiPlanner.entity.User;
import com.FYP.PandaiPlanner.repository.BudgetRepository;
import com.FYP.PandaiPlanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BudgetService {
    private final BudgetRepository  budgetRepository;
    private final UserRepository  userRepository;

    @Autowired
    public BudgetService(BudgetRepository budgetRepository, UserRepository userRepository) {
        this.budgetRepository = budgetRepository;
        this.userRepository = userRepository;
    }
//    public void createBudget(BudgetDTO budgetDTO) {
//        Budget budget = new Budget();
//        // Assuming you have a method to find the user by ID, and it's set in the DTO
//        User user = userRepository.findById(budgetDTO.getUserId()).orElseThrow(() -> new NoSuchElementException());
//
//        budget.setUser(user); // You might need to adjust this based on your user handling logic
//        budget.setBudgetCategory(budgetDTO.getBudgetCategory());
//        budget.setBudgetLimit(budgetDTO.getBudgetLimit());
//        budget.setBudgetDate(budgetDTO.getBudgetDate());
//        budget.setBudgetSpent(0);
//
//        // Save the budget entity to the database
//        budgetRepository.save(budget);
//    }


    public void createOrUpdateBudget(BudgetDTO budgetDTO) {
        User user = userRepository.findById(budgetDTO.getUserId())
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + budgetDTO.getUserId()));

        Optional<Budget> existingBudget = budgetRepository.findExistingBudgetByCategoryAndDate(
                user.getId(),
                budgetDTO.getBudgetCategory(),
                budgetDTO.getBudgetDate()
        );

        Budget budget;
        if (existingBudget.isPresent()) {
            // If the budget exists for the current month and category, update it
            budget = existingBudget.get();
            budget.setBudgetLimit(budgetDTO.getBudgetLimit());  // Assuming you want to update the budget limit
            budget.setBudgetSpent(budgetDTO.getBudgetSpent());  // And any other fields you want to update
        } else {
            // If not, create a new budget
            budget = new Budget();
            budget.setUser(user);
            budget.setBudgetCategory(budgetDTO.getBudgetCategory());
            budget.setBudgetLimit(budgetDTO.getBudgetLimit());
            budget.setBudgetDate(budgetDTO.getBudgetDate());
            budget.setBudgetSpent(budgetDTO.getBudgetSpent());
        }

        // Save the budget entity to the database
        budgetRepository.save(budget);
    }

    public List<Budget> findCurrentMonthBudgets(Long userId) {
        LocalDate currentDate = LocalDate.now(); // Use java.util.Date (or use java.time.LocalDate with appropriate conversions)
        System.out.println("the user id is:"+ userId + "the data is:" + currentDate);

        return budgetRepository.findExistingBudgetByUserIDAndDate(userId, currentDate);
    }









}
