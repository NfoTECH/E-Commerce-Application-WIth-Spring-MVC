package com.fortunate.week7tasknfotech.repository;


import com.fortunate.week7tasknfotech.model.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartRepository extends CrudRepository<Cart, Long> {

    List<Cart> findAllByCustomerId (Long customerId);
}


