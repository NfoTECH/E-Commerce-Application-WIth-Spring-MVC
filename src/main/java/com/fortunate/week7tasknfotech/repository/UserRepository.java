package com.fortunate.week7tasknfotech.repository;

import com.fortunate.week7tasknfotech.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
