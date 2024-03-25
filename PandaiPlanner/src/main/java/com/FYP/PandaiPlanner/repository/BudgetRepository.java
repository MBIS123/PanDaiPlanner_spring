package com.FYP.PandaiPlanner.repository;

import com.FYP.PandaiPlanner.entity.Budget;
import com.FYP.PandaiPlanner.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    // This method is provided by JpaRepository, so you don't need to define it yourself unless you're changing the functionality.
    // Optional<Budget> findById(Long id); // This line can be removed

    @Query("SELECT b FROM Budget b WHERE b.user.id = :userId AND b.budgetCategory = :category AND b.budgetDate = :budgetDate")
    List<Budget> findByUserIdAndCategoryAndBudgetDate(@Param("userId") Long userId, @Param("category") String category, @Param("budgetDate") LocalDate budgetDate);

    // If you want to find budgets by user ID and date without category
    @Query("SELECT b FROM Budget b WHERE b.user.id = :userId AND b.budgetDate = :budgetDate")
    List<Budget> findByUserIdAndBudgetDate(@Param("userId") Long userId, @Param("budgetDate") LocalDate budgetDate);
}
