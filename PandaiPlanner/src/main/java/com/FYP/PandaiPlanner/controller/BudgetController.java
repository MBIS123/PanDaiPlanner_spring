package com.FYP.PandaiPlanner.controller;

import com.FYP.PandaiPlanner.dto.UserDTO;
import com.FYP.PandaiPlanner.entity.User;
import com.FYP.PandaiPlanner.service.BudgetService;
import com.FYP.PandaiPlanner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/budget")
public class BudgetController {

    private final BudgetService budgetService;

    @Autowired
    public BudgetController(BudgetService userService) {
        this.budgetService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<User> createNewUser(@RequestBody UserDTO userDTO) {
        // Perform validation on email and password here
        //String encodedPassword = passwordEncoder.encode(user.getPassword());
        User newUser = new User();
        newUser.setEmail(userDTO.getEmail());
        newUser.setPassword(userDTO.getPassword());
        newUser.setFirstName(userDTO.getFirstName());
        newUser.setLastName(userDTO.getLastName());
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }




}
