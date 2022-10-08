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
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public UserController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/")
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

    @GetMapping("/login")
    public String viewLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/logUser")
    public String login(HttpSession session, @ModelAttribute("user") User user,
                        RedirectAttributes redirectAttributes) {

        String message = userService.userSignIn(user.getEmail(), user.getPassword());
        if(message.equals("customer")){
            System.out.println("Customer logged in");
            session.setAttribute("email" , user.getEmail());
            return "redirect:/";
        } else if (message.equals("admin")) {
            System.out.println("Admin logged in");
            session.setAttribute("email" , user.getEmail());
            return "redirect:/admin/dashboard";
        }else{
            redirectAttributes.addFlashAttribute("message", message);
            return "redirect:login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userLogin");
        session.invalidate();
        return "redirect:/";
    }

}
