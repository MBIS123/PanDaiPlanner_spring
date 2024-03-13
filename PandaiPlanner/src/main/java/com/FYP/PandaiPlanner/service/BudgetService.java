package com.FYP.PandaiPlanner.service;

import com.FYP.PandaiPlanner.repository.BudgetRepository;
import com.FYP.PandaiPlanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {

    private final BudgetRepository  budgetRepository;

    @Autowired
    public BudgetService(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }
}
