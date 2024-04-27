package com.FYP.PandaiPlanner.service;


import com.FYP.PandaiPlanner.dto.FinancialPlanDTO;
import com.FYP.PandaiPlanner.entity.FinancialPlan;
import com.FYP.PandaiPlanner.entity.User;
import com.FYP.PandaiPlanner.repository.FinancialPlanRepository;
import com.FYP.PandaiPlanner.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FinancialPlanService {



    private final FinancialPlanRepository financialPlanRepository;

    private final UserRepository userRepository;

    public FinancialPlanService(FinancialPlanRepository financialPlanRepository, UserRepository userRepository) {
        this.financialPlanRepository = financialPlanRepository;
        this.userRepository = userRepository;
    }


    public void createFinancialPlan(FinancialPlanDTO financialPlanDTO) {
        // Find the user associated with the financial plan
        User user = userRepository.findById(financialPlanDTO.getUserId())
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + financialPlanDTO.getUserId()));

        // Create a new FinancialPlan instance
        FinancialPlan financialPlan = new FinancialPlan();
        financialPlan.setUser(user);
        financialPlan.setPlanName(financialPlanDTO.getPlanName());
        financialPlan.setTargetAmount(financialPlanDTO.getTargetAmount());
        financialPlan.setCreatePlanDate(financialPlanDTO.getCreatePlanDate());
        financialPlan.setSuccessScore(financialPlanDTO.getSuccessScore());
        financialPlan.setAssessment(financialPlanDTO.getAssessment());
        financialPlan.setFinancialAdvice(financialPlanDTO.getFinancialAdvice());
        financialPlan.setFinancialAdjustment(financialPlanDTO.getFinancialAdjustment());

        // Save the financial plan
        financialPlanRepository.save(financialPlan);
    }

    public List<FinancialPlan> findFinancialPlan(Long userId) {
        // Convert year and month to a LocalDate object
        System.out.println("the user id is:" + userId );
        return financialPlanRepository.findFinancialPlanByUserId(userId);
    }


    public void deleteFinancial(Long financialPlanId) {
        FinancialPlan financialPlan = financialPlanRepository.findById(financialPlanId)
                .orElseThrow(() -> new NoSuchElementException("Financial plan not found with id: " + financialPlanId));

        financialPlanRepository.delete(financialPlan);
    }





}
