package com.fortunate.week7tasknfotech.repository;

import com.fortunate.week7tasknfotech.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmailAndPassword(String email, String password);
    Optional<User> findUserByEmail(String email);
    User getUserByEmail(String email);

}
