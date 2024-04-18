package com.FYP.PandaiPlanner.dto;

import com.FYP.PandaiPlanner.entity.User;

import java.time.LocalDate;

public class BudgetDTO {


    private long userId; // Assuming the user ID is a long. Use int if it's an integer
    private String budgetCategory;
    private double budgetLimit;
    private LocalDate budgetDate;
    private double budgetSpent;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
    public String getBudgetCategory() {
        return budgetCategory;
    }

    public void setBudgetCategory(String budgetCategory) {
        this.budgetCategory = budgetCategory;
    }

    public double getBudgetLimit() {
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
