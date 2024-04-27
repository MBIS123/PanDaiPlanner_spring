package com.FYP.PandaiPlanner.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "financial_plan")
public class FinancialPlan {

    @Id
    @SequenceGenerator(
            name = "financial_plan_sequence",
            sequenceName = "financial_plan_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "financial_plan_sequence"
    )
    @Column(name = "financial_plan_id")
    private Long financialPlanId;

    @Column(name = "plan_name")
    private String planName;

    @Column(name = "target_amount")
    private double targetAmount;

    @Column(name = "create_plan_date")
    private LocalDate createPlanDate;

    @Column(name = "success_score")
    private String successScore; // Changed to String as per requirement

    @Column(name = "assessment", columnDefinition = "VARCHAR(10485760)")
    private String assessment;

    @Column(name = "financial_advice", columnDefinition = "VARCHAR(10485760)")
    private String financialAdvice;

    @Column(name = "financial_adjustment", columnDefinition = "VARCHAR(10485760)")
    private String financialAdjustment;


    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    // Constructors
    public FinancialPlan() {
    }

    public FinancialPlan(String planName, double targetAmount, String successScore, String assessment, String financialAdvice, String financialAdjustment, User user) {
        this.planName = planName;
        this.targetAmount = targetAmount;
        this.successScore = successScore;
        this.assessment = assessment;
        this.financialAdvice = financialAdvice;
        this.financialAdjustment = financialAdjustment;
        this.user = user;
    }

    @Override
    public String toString() {

        return "FinancialPlan{" +
                "financialPlanId=" + financialPlanId +
                ", planName='" + planName + '\'' +
                ", targetAmount=" + targetAmount +
                ", successScore='" + successScore + '\'' +
                ", assessment='" + assessment + '\'' +
                ", financialAdvice='" + financialAdvice + '\'' +
                ", financialAdjustment='" + financialAdjustment + '\'' +
                ", user=" + user +
                '}';
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Long getFinancialPlanId() {
        return financialPlanId;
    }

    public void setFinancialPlanId(Long financialPlanId) {
        this.financialPlanId = financialPlanId;
    }

    public LocalDate getCreatePlanDate() {
        return createPlanDate;
    }

    public void setCreatePlanDate(LocalDate createPlanDate) {
        this.createPlanDate = createPlanDate;
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

}
