package com.FYP.PandaiPlanner.repository;

import com.FYP.PandaiPlanner.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IncomeRepository extends JpaRepository<Income, Long> {

    @Query("SELECT i FROM Income i WHERE i.user.id = :userId")
    List<Income> findExistingIncomeByUserId(@Param("userId") Long userId);

    @Query("SELECT i FROM Income i WHERE i.user.id = :userId AND i.incomeType = :incomeType")
    Optional<Income> findExistingIncomeByUserIdAndIncomeType(
            @Param("userId") Long userId,
            @Param("incomeType") String incomeType
    );
}