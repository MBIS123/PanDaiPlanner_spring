package com.FYP.PandaiPlanner.service;

import com.FYP.PandaiPlanner.dto.IncomeDTO;
import com.FYP.PandaiPlanner.entity.Income;
import com.FYP.PandaiPlanner.entity.User;
import com.FYP.PandaiPlanner.repository.IncomeRepository;
import com.FYP.PandaiPlanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class IncomeService {


    private final IncomeRepository incomeRepository;
    private final UserRepository userRepository;

    @Autowired
    public IncomeService(IncomeRepository incomeRepository, UserRepository userRepository) {
        this.incomeRepository = incomeRepository;
        this.userRepository = userRepository;
    }

    public void createOrUpdateIncome(IncomeDTO incomeDTO) {
        User user = userRepository.findById(incomeDTO.getUserId())
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + incomeDTO.getUserId()));



        Optional<Income> existingIncome = incomeRepository.findExistingIncomeByUserIdAndIncomeType(
                user.getId(),
                incomeDTO.getIncomeType()
        );
        System.out.println("THe user is:" +user.toString());
        System.out.println("THe existingIncome is:" +existingIncome.toString());


        // Create a new Income entity
        Income income = existingIncome.orElse(new Income());

        System.out.println("IncomeDTO is" + incomeDTO.toString());
        // Set properties of the Income entity from the DTO
        income.setUser(user);
        income.setIncomeType(incomeDTO.getIncomeType());
        income.setAmount(incomeDTO.getAmount());
        income.setDuration(0);

        // Save the Income entity to the database
        incomeRepository.save(income);
    }

    public List<IncomeDTO> findAllIncome(Long userId) {
        List<Income> userIncome = incomeRepository.findExistingIncomeByUserId(userId);
        List<IncomeDTO> userIncomeDTO = new ArrayList<>();
        for (Income income : userIncome) {
            IncomeDTO incomeDTO = convertToDTO(income);
            userIncomeDTO.add(incomeDTO);
        }
        return userIncomeDTO;
    }

    private IncomeDTO convertToDTO(Income income) {
        IncomeDTO incomeDTO = new IncomeDTO();
        incomeDTO.setIncomeId(income.getIncomeId());
        incomeDTO.setIncomeType(income.getIncomeType());
        incomeDTO.setAmount(income.getAmount());
        incomeDTO.setDuration(income.getDuration());
        incomeDTO.setUserId(income.getUser().getId());
        // Set other properties as needed
        return incomeDTO;
    }

}
