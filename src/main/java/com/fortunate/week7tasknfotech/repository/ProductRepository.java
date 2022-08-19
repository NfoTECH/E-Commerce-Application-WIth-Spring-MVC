package com.fortunate.week7tasknfotech.repository;

import com.fortunate.week7tasknfotech.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
}
