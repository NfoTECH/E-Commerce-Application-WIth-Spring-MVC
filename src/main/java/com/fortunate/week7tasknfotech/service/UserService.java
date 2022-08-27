package com.fortunate.week7tasknfotech.service;

import com.fortunate.week7tasknfotech.enums.Role;
import com.fortunate.week7tasknfotech.model.User;
import com.fortunate.week7tasknfotech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepo;
    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public void saveNewUser(User newUser) {
        userRepo.save(newUser);
    }
    public User getUserByEmailAndPassword(String email, String password) {
        User user = userRepo.findUserByEmailAndPassword(email, password);
        return user;
    }
    public User getUserByEmail(String email) {
        User user = userRepo.findUserByEmail(email);
        return user;
    }
    public List<User> getAllUsers() {
        List<User> userList = (List<User>) userRepo.findAll();
        return userList;
    }




}
