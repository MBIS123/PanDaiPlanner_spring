package com.FYP.PandaiPlanner.repository;

import com.FYP.PandaiPlanner.entity.Budget;
import com.FYP.PandaiPlanner.entity.FinancialPlan;
import com.FYP.PandaiPlanner.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface FinancialPlanRepository extends JpaRepository<FinancialPlan, Long>
{



    @Query("SELECT fp FROM FinancialPlan fp WHERE fp.user.id = :userId")
    List<FinancialPlan> findFinancialPlanByUserId(Long userId);



}
