package com.FYP.PandaiPlanner.dto;

import java.time.LocalDate;

public class FinancialPlanDTO {

    private Long financialPlanId;



    private long userId; // Assuming the user ID is a long. Use int if it's an integer

    private String planName;
    private double targetAmount;
    private LocalDate createPlanDate;
    private String successScore;
    private String assessment;
    private String financialAdvice;
    private String financialAdjustment;

    // Constructors
    public FinancialPlanDTO() {
    }

    public FinancialPlanDTO(Long financialPlanId, String planName, double targetAmount, LocalDate transactionDate, String successScore, String assessment, String financialAdvice, String financialAdjustment) {
        this.financialPlanId = financialPlanId;
        this.planName = planName;
        this.targetAmount = targetAmount;
        this.createPlanDate = transactionDate;
        this.successScore = successScore;
        this.assessment = assessment;
        this.financialAdvice = financialAdvice;
        this.financialAdjustment = financialAdjustment;
    }

    // Getters and Setters

    public Long getFinancialPlanId() {
        return financialPlanId;
    }

    public void setFinancialPlanId(Long financialPlanId) {
        this.financialPlanId = financialPlanId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public LocalDate getCreatePlanDate() {
        return createPlanDate;
    }

    public void setCreatePlanDate(LocalDate createPlanDate) {
        this.createPlanDate = createPlanDate;
    }

    public String getSuccessScore() {
        return successScore;
    }

    public void setSuccessScore(String successScore) {
        this.successScore = successScore;
    }

    public String getAssessment() {
        return assessment;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }

    public String getFinancialAdvice() {
        return financialAdvice;
    }

    public void setFinancialAdvice(String financialAdvice) {
        this.financialAdvice = financialAdvice;
    }

    public String getFinancialAdjustment() {
        return financialAdjustment;
    }

    public void setFinancialAdjustment(String financialAdjustment) {
        this.financialAdjustment = financialAdjustment;
    }

    // Optional: You can override toString() method if needed

    @Override
    public String toString() {
        return "FinancialPlanDTO{" +
                "financialPlanId=" + financialPlanId +
                ", planName='" + planName + '\'' +
                ", targetAmount=" + targetAmount +
                ", transactionDate=" + createPlanDate +
                ", successScore='" + successScore + '\'' +
                ", assessment='" + assessment + '\'' +
                ", financialAdvice='" + financialAdvice + '\'' +
                ", financialAdjustment='" + financialAdjustment + '\'' +
                '}';
    }
}
