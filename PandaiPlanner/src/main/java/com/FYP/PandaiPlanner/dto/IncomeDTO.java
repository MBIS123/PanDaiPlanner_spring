package com.FYP.PandaiPlanner.dto;

public class IncomeDTO {

    private Long incomeId;
    private String incomeType;
    private double amount;
    private int duration;
    private Long userId;

    // Constructors

    public IncomeDTO() {
    }

    public IncomeDTO(Long incomeId, String incomeType, double amount, int duration, Long userId) {
        this.incomeId = incomeId;
        this.incomeType = incomeType;
        this.amount = amount;
        this.duration = duration;
        this.userId = userId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    // toString method

    @Override
    public String toString() {
        return "IncomeDTO{" +
                "incomeId=" + incomeId +
                ", incomeType='" + incomeType + '\'' +
                ", amount=" + amount +
                ", duration=" + duration +
                ", userId=" + userId +
                '}';
    }
}
