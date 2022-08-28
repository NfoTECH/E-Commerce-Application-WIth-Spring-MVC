package com.fortunate.week7tasknfotech.service;

import com.fortunate.week7tasknfotech.Exception.UserNotFoundException;
import com.fortunate.week7tasknfotech.model.enums.Role;
import com.fortunate.week7tasknfotech.model.User;
import com.fortunate.week7tasknfotech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {
    private UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public void saveNewUser(User newUser) {
        userRepo.save(newUser);
    }

    public String userSignIn(String email, String password) {
        String message = "";
        User user = userRepo.getUserByEmail(email);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                if (user.getRole().equals("ADMIN")) {
                    message = "admin";
                } else if (user.getRole().equals("CUSTOMER")) {
                    message = "customer";
                }
            } else {
                message = "Invalid password";
            }
        } else {
            message = "User not found";
        }
        return message;
        }

    public User getUserByEmail(String email) {
        User user = userRepo.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException("This user was not found"));
        return user;
    }
    public List<User> getAllUsers() {
        List<User> userList = (List<User>) userRepo.findAll();
        return userList;
    }




}
