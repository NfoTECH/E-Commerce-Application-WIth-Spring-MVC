package com.fortunate.week7tasknfotech.service;

import com.fortunate.week7tasknfotech.enums.Role;
import com.fortunate.week7tasknfotech.model.User;
import com.fortunate.week7tasknfotech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private static UserRepository userRepository;

    public User registerUser(String firstname, String lastname, String phone, String email, String password) {
        if(firstname != null && lastname != null && phone != null && email !=null && password != null) {
            User user = new User();
            user.setFirstName(firstname);
            user.setLastName(lastname);
            user.setEmail(email);
            user.setPassword(password);
            return userRepository.save(user);

        } else {
            return null;
        }
    }

    public static User authenticate(String email, String password) {
        return userRepository.findByEmailAndPassword(email,password).orElse(null);
    }
}
