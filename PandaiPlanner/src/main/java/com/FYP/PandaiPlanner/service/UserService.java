package com.FYP.PandaiPlanner.service;


import com.FYP.PandaiPlanner.dto.UserDTO;
import com.FYP.PandaiPlanner.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.FYP.PandaiPlanner.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository  userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    //service to add new registered user to database
    public void addNewUser(User user) {
        Optional<User> userByEmail = userRepository.findByEmail(user.getEmail());
        if (userByEmail.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        user.setPassword(user.getPassword());
        userRepository.save(user);
    }
    public User getUserById(Long userID){
        User userFound = userRepository.findUserById(userID)
                .orElseThrow(() -> new IllegalStateException("User does not existsss"));
        return  userFound;
    }

    @Transactional
    public void updateUserName(UserDTO userDTO) {
        Long userId = userDTO.getId();
        String newName = userDTO.getName();

        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(newName);
            userRepository.save(user);
        } else {
            throw new IllegalStateException("User with ID " + userId + " not found");
        }
    }

    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException("User with id " + userId + " does not exist");
        }
        userRepository.deleteById(userId);
    }



    public boolean validateUser(User user) {
        User userFound = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new IllegalStateException("User does not existsss"));

        if( !user.getPassword().equals(userFound.getPassword())){
            throw new IllegalStateException("Password incorrect");
        }
        return true;
    }

    public Optional<Long> validateUserAndGetId(User loginUser) {
        return userRepository.findByEmail(loginUser.getEmail())
                .filter(user -> user.getPassword().equals(loginUser.getPassword())) // Direct password comparison
                .map(User::getId); // Extract and return the user ID
    }






    @Transactional
    public void updateUser(User user) {
        Optional<User> existingUser = userRepository.findById(user.getId());
        if (existingUser.isPresent()) {
            User updateUser = existingUser.get();
          //  updateUser.setName(user.getName());
            userRepository.save(updateUser);
        } else {
            throw new IllegalStateException("No ID found");
        }
    }
}