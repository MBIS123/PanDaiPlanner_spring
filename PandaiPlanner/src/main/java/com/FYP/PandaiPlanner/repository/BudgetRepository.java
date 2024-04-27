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

    @Query("SELECT b FROM Budget b WHERE b.user.id = :userId AND b.budgetCategory = :category AND b.budgetDate = :budgetDate")
    List<Budget> findByUserIdAndCategoryAndBudgetDate(@Param("userId") Long userId, @Param("category") String category, @Param("budgetDate") LocalDate budgetDate);

    // If you want to find budgets by user ID and date without category
    @Query("SELECT b FROM Budget b WHERE b.user.id = :userId AND b.budgetDate = :budgetDate")
    List<Budget> findByUserIdAndBudgetDate(@Param("userId") Long userId, @Param("budgetDate") LocalDate budgetDate);

    @Query("SELECT b FROM Budget b WHERE b.user.id = :userId AND b.budgetCategory = :budgetCategory AND EXTRACT(YEAR FROM CAST(b.budgetDate AS date)) = EXTRACT(YEAR FROM CAST(:date AS date)) AND EXTRACT(MONTH FROM CAST(b.budgetDate AS date)) = EXTRACT(MONTH FROM CAST(:date AS date))")
    Optional<Budget> findExistingBudgetByCategoryAndDate(Long userId, String budgetCategory, LocalDate date);

    @Query("SELECT b FROM Budget b WHERE b.user.id = :userId " +
            "AND FUNCTION('YEAR', b.budgetDate) = FUNCTION('YEAR', CURRENT_DATE) " +
            "AND FUNCTION('MONTH', b.budgetDate) = FUNCTION('MONTH', CURRENT_DATE)")
    List<Budget> findAllByUserIdAndCurrentMonth(Long userId);

    @Query("SELECT b FROM Budget b WHERE b.user.id = :userId ")
    List<Budget> findAllByUserId(Long userId);

    @Query("SELECT b FROM Budget b WHERE b.user.id = :userId AND EXTRACT(YEAR FROM CAST(b.budgetDate AS date)) = EXTRACT(YEAR FROM CAST(:date AS date)) AND EXTRACT(MONTH FROM CAST(b.budgetDate AS date)) = EXTRACT(MONTH FROM CAST(:date AS date))")
    List<Budget> findExistingBudgetByUserIDAndDate(Long userId, LocalDate date);

}
