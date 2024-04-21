package com.FYP.PandaiPlanner.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "budget")
public class Budget {
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "budget_category")
    private String budgetCategory;

    @Column(name = "budget_limit")
    private double budgetLimit;

    @Column(name = "budget_date")
    private LocalDate budgetDate;

    @Column(name = "budget_spent")
    private double budgetSpent;

    @Column(name = "budget_color")
    private String budgetColor; // Store color as a string

    @Column(name = "budget_icon")
    private String budgetIcon; // Store icon as a string (e.g., icon name or URL)

    @Id
    @SequenceGenerator(
            name = "budget_sequence",
            sequenceName = "budget_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "budget_sequence"
    )
    private Long budgetId;

    // Getters and setters
    // Constructors

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public void setBudgetLimit(double budgetLimit) {
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

    public String getBudgetColor() {
        return budgetColor;
    }

    public void setBudgetColor(String budgetColor) {
        this.budgetColor = budgetColor;
    }

    public String getBudgetIcon() {
        return budgetIcon;
    }

    public void setBudgetIcon(String budgetIcon) {
        this.budgetIcon = budgetIcon;
    }

    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }


}
