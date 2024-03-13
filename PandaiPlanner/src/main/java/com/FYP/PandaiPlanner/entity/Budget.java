package com.FYP.PandaiPlanner.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "\"budget\"")
public class Budget {
    private Long userId;
    private String budgetCategory;
    private int budgetLimit;
    private LocalDate budgetDate;
    private double budgetSpent;

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long budgetId;
    public Long getBudgetId() {
        return budgetId;
    }
    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBudgetCategory() {
        return budgetCategory;
    }

    public void setBudgetCategory(String budgetCategory) {
        this.budgetCategory = budgetCategory;
    }

    public int getBudgetLimit() {
        return budgetLimit;
    }

    public void setBudgetLimit(int budgetLimit) {
        this.budgetLimit = budgetLimit;
    }

    public LocalDate getBudgetDate() {
        return budgetDate;
    }

    public void setBudgetDate(LocalDate budgetDate) {
        this.budgetDate = budgetDate;
    }

    public double getBudgetSpent() {
        return budgetSpent;
    }

    public void setBudgetSpent(double budgetSpent) {
        this.budgetSpent = budgetSpent;
    }





}
