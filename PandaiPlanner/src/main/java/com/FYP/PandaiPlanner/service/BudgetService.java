package com.FYP.PandaiPlanner.service;

import com.FYP.PandaiPlanner.dto.BudgetDTO;
import com.FYP.PandaiPlanner.entity.Budget;
import com.FYP.PandaiPlanner.entity.Transaction;
import com.FYP.PandaiPlanner.entity.User;
import com.FYP.PandaiPlanner.repository.BudgetRepository;
import com.FYP.PandaiPlanner.repository.TransactionRepository;
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

    private final TransactionRepository transactionRepository;


    @Autowired
    public BudgetService(BudgetRepository budgetRepository, UserRepository userRepository, TransactionRepository transactionRepository) {
        this.budgetRepository = budgetRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
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
            budget = existingBudget.get();
            budget.setBudgetLimit(budgetDTO.getBudgetLimit());
            System.out.println("the budget limit is: " + budget.getBudgetLimit());
        } else {
            budget = new Budget();
            budget.setUser(user);
            budget.setBudgetCategory(budgetDTO.getBudgetCategory());
            budget.setBudgetLimit(budgetDTO.getBudgetLimit());
            budget.setBudgetDate(budgetDTO.getBudgetDate());
        }
        List<Transaction> existingTransactions = transactionRepository.findTransactionByUserIDDateAndBudgetCategory(
                user.getId(),
                budgetDTO.getBudgetDate(),
                budgetDTO.getBudgetCategory()
        );
        double totalTransactionAmount = existingTransactions.stream()
                .mapToDouble(Transaction::getTransactionAmount)
                .sum();
        budget.setBudgetSpent(totalTransactionAmount);
        // Save the budget entity to the database
        budgetRepository.save(budget);
    }

    public void recordBudgetSpent(BudgetDTO budgetDTO) {
        // Retrieve the existing budget based on userId, budgetCategory, and budgetDate
        Optional<Budget> existingBudgetOpt = budgetRepository.findExistingBudgetByCategoryAndDate(
                budgetDTO.getUserId(),
                budgetDTO.getBudgetCategory(),
                budgetDTO.getBudgetDate()
        );

        if (existingBudgetOpt.isPresent()) {
            Budget existingBudget = existingBudgetOpt.get();

            double newBudgetSpent = existingBudget.getBudgetSpent() + budgetDTO.getBudgetSpent();

            existingBudget.setBudgetSpent(newBudgetSpent);

            budgetRepository.save(existingBudget);
        } else {
            // handle the case where no existing budget was found.


        }
    }

    public List<Budget> findCurrentMonthBudgets(Long userId) {
        LocalDate currentDate = LocalDate.now(); // Use java.util.Date (or use java.time.LocalDate with appropriate conversions)
        System.out.println("the user id is:"+ userId + "the data is:" + currentDate);

        return budgetRepository.findExistingBudgetByUserIDAndDate(userId, currentDate);
    }

    public List<Budget> findBudgetsForMonth(Long userId, int year, int month) {
        // Convert year and month to a LocalDate object
        LocalDate currentDate = LocalDate.of(year, month, 1); // Set day to 1 for the first day of the month
        System.out.println("the user id is:" + userId + " the date is:" + currentDate);

        return budgetRepository.findExistingBudgetByUserIDAndDate(userId, currentDate);
    }

}