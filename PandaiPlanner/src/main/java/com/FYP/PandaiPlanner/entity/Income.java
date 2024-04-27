package com.FYP.PandaiPlanner.entity;

import jakarta.persistence.*;
@Entity
@Table(name = "income")
public class Income {

    @Id
    @SequenceGenerator(
            name = "income_sequence",
            sequenceName = "income_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "income_sequence"
    )
    @Column(name = "income_id")
    private Long incomeId;

    @Column(name = "income_type")
    private String incomeType;

    @Column(name = "amount")
    private double amount;

    @Column(name = "duration")
    private int duration;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;


    public Income() {
    }

    public Income(String incomeType, double amount, int duration, Long userId) {
        this.incomeType = incomeType;
        this.amount = amount;
        this.duration = duration;
    }

    // Getters and setters

    public Long getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(Long incomeId) {
        this.incomeId = incomeId;
    }

    public String getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // toString method

    @Override
    public String toString() {
        return "Income{" +
                "incomeId=" + incomeId +
                ", incomeType='" + incomeType + '\'' +
                ", amount=" + amount +
                ", duration=" + duration +
                '}';
    }
}
