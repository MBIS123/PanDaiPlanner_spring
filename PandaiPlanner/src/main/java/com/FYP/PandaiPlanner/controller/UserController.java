package com.FYP.PandaiPlanner.controller;
import com.FYP.PandaiPlanner.dto.UserDTO;
import com.FYP.PandaiPlanner.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.FYP.PandaiPlanner.service.UserService;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
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
        userService.addNewUser(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> validateLogin(@RequestBody User user) {
        try {
            Optional<Long> userId = userService.validateUserAndGetId(user);
            if (userId.isPresent()) {
                // Login successful
                Map<String, Long> userIdMap = Collections.singletonMap("userId", userId.get());
                return ResponseEntity.ok().body(userIdMap);
            } else {
                // User not found or password does not match
                return new ResponseEntity<>("Invalid login details", HttpStatus.UNAUTHORIZED);
            }
        } catch (IllegalStateException e) {
            // If validation fails, catch the exception and return an appropriate response
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }


    }


    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }
    @PostMapping
    public void addNewUser(@RequestBody User user) {
        userService.addNewUser(user);
    }
    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long id) {
        userService.deleteUser(id);
    }
    @PutMapping
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }
}