package com.fortunate.week7tasknfotech.service;


import com.fortunate.week7tasknfotech.model.Cart;
import com.fortunate.week7tasknfotech.repository.CartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private CartRepository cartRepo;

    @Autowired
    public CartService(CartRepository cartRepo) {
        this.cartRepo = cartRepo;
    }

    public void addToCart(Cart cartEntry) {
        cartRepo.save(cartEntry);
    }


    public List<Cart> getCustomerCartById(Long customerId) {
        return cartRepo.findAllByCustomerId(customerId);
    }


    public void deleteCartContentById(Long cartId) {
        cartRepo.deleteById(cartId);
    }

    public void updateCart(Long id) {
        Optional<Cart> myCart = cartRepo.findById(id);
        Cart cart = myCart.get();
        cart.setQuantity(myCart.get().getQuantity() + 1);
        cartRepo.save(cart);
    }

    public void downgradeCart(Long id) {
        Optional<Cart> myCart = cartRepo.findById(id);
        Cart cart = myCart.get();
        cart.setQuantity(myCart.get().getQuantity() - 1);
        cartRepo.save(cart);


    }
}
