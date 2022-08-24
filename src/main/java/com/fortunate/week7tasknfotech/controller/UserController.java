package com.fortunate.week7tasknfotech.controller;


import com.fortunate.week7tasknfotech.enums.Role;
import com.fortunate.week7tasknfotech.model.Product;
import com.fortunate.week7tasknfotech.model.User;
import com.fortunate.week7tasknfotech.repository.UserRepository;
import com.fortunate.week7tasknfotech.service.ProductService;
import com.fortunate.week7tasknfotech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ProductService service;

    @GetMapping("")
    public String viewHomePage(Model model) {
        List<Product> displayProducts = service.listAllProduct();
        model.addAttribute("displayProducts", displayProducts);
        return "index";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "signUp";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        repository.save(user);
        return "signUpSuccess";
    }

    @GetMapping("/login")
    public String viewLoginPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        return "redirect:/";
    }

//    @PostMapping("/login")
//    public String login(@ModelAttribute User users, Model model) {
//        System.out.println("login request:"+ users);
//        User login = UserService.authenticate(users.getEmail(),users.getPassword());
//        if (login != null && login.getRole().equals(Role.Customer)  ) {
//            System.out.println(login);
//            model.addAttribute("userLogin",login.getFirstName());
//            return "allProduct";
//        } else if (login != null && login.getRole().equals(Role.Admin)  ) {
//            System.out.println(login);
//            model.addAttribute("userLogin", login.getFirstName());
//            return "allProduct";
//        } else {
//            return "errorPage";
//        }
//    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = repository.findAll();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

    @GetMapping("/contact")
    public String contactPage() {
        return "contact";
    }


}
