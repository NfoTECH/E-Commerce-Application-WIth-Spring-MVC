package com.fortunate.week7tasknfotech.controller;


import com.fortunate.week7tasknfotech.model.Cart;
import com.fortunate.week7tasknfotech.model.User;
import com.fortunate.week7tasknfotech.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CartController {
    CartService cartService;


    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cartContent")
    public String displayCustomerCart(Model model, HttpSession session) {
        User loggedUser = (User) session.getAttribute("loggedUser");
        List<Cart> cartList = cartService.getCustomerCartById(loggedUser.getId());
        model.addAttribute("cartList", cartList);
        return "cart";
    }

    @GetMapping("/editCart/{cartId}")
    public String removeCartContent(@PathVariable Long cartId){
        cartService.deleteCartContentById(cartId);
        return "redirect:/cartContent";
    }

    @GetMapping("/decrementQuantity/{cartId}")
    public String reduceQuantity(@PathVariable("cartId") Long cartId) {
        cartService.downgradeCart(cartId);
        return "redirect:/cartContent";
    }

    @GetMapping("/incrementQuantity/{cartId}")
    public String increaseQuantity(@PathVariable("cartId") Long cartId) {
        cartService.updateCart(cartId);
        return "redirect:/cartContent";
    }


    @GetMapping("/addToCart/{productName}")
    public String addToCart(@PathVariable("productName") String productName, HttpSession httpSession) {
        User loggedUser = (User) httpSession.getAttribute("loggedUser");
        Cart newCart = new Cart();
        newCart.setProduct(productName);
        newCart.setQuantity(1L);
        newCart.setCustomerId(loggedUser.getId());
        cartService.addToCart(newCart);
        return "redirect:/cartContent";
    }

}