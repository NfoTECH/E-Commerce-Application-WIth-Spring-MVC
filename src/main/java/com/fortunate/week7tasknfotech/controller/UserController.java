package com.fortunate.week7tasknfotech.controller;

import com.fortunate.week7tasknfotech.model.Product;
import com.fortunate.week7tasknfotech.model.User;
import com.fortunate.week7tasknfotech.service.ProductService;
import com.fortunate.week7tasknfotech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    private UserService userService;
    private ProductService productService;

    @Autowired
    public UserController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("")
    public String viewHomePage(Model model) {
        List<Product> displayProducts = productService.listAllProduct();
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
        userService.saveNewUser(user);
        return "signUpSuccess";

    }

    @GetMapping("/auth")
    public String viewLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpSession session, @ModelAttribute("user") User user,
                        RedirectAttributes redirectAttributes) {
        User loggingUser;
        loggingUser = userService.getUserByEmail(user.getEmail());
        if(loggingUser == null) {
            redirectAttributes.addFlashAttribute("Invalid", "User does not exist, check login details or register");

            return "login";
        }
        loggingUser = userService.getUserByEmailAndPassword(user.getEmail(), user.getPassword());
        if(loggingUser == null) {
            redirectAttributes.addFlashAttribute("Invalid", "Incorrect Password");
            return "login";
        }
        session.setAttribute("loggedUser", loggingUser);
        return "redirect:/productsPage";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userService.getAllUsers();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userLogin");
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/contact")
    public String contactPage() {
        return "contact";
    }


}
