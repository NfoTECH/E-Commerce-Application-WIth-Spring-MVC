package com.fortunate.week7tasknfotech.controller;

import com.fortunate.week7tasknfotech.model.Product;
import com.fortunate.week7tasknfotech.model.User;
import com.fortunate.week7tasknfotech.service.ProductService;
import com.fortunate.week7tasknfotech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "admin" )
public class AdminController {

    private final UserService userService;

    private final ProductService service;

    @Autowired
    public AdminController(UserService userService, ProductService service) {
        this.userService = userService;
        this.service = service;
    }

    @GetMapping( value="/register")
    public String registerPage(Model model){
        model.addAttribute("admin" , new User());
        return "Admin/register";
    }


    @PostMapping(value="/registerAdmin")
    public String registerAdmin(@ModelAttribute User admin ){
        userService.saveNewUser(admin);
        return "redirect:/admin/loginAdmin";
    }

    @GetMapping( value="/login")
    public String loginPage(Model model){
        model.addAttribute("loggedInAdmin" , new User());
        return "Admin/login";
    }


    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userService.getAllUsers();
        model.addAttribute("listUsers", listUsers);
        return "Admin/users";
    }

    @GetMapping( value="/dashboard")
    public String dashboard(Model model , HttpSession session){
        List<Product> productList = service.listAllProduct();
        String email = (String) session.getAttribute("email");
        User admin = userService.getUserByEmail( email);
        model.addAttribute("admin" , new User());
        model.addAttribute("products" , productList);
        model.addAttribute("id" , admin.getId());
        model.addAttribute("product" , new Product());
        model.addAttribute("productField" , new Product());

        if(session.getAttribute("email") == null){
            return "redirect:/login";
        }else {
            return "Admin/dashboard";
        }
    }

    @GetMapping(value = "/getProduct/{productId}")
    public String getProduct(@PathVariable(name="productId") String productId , Model model){
        Long id = Long.parseLong(productId);
        System.out.println(service.get(id));
        model.addAttribute("product" , service.get(id));
        return "Admin/product";
    }

    @GetMapping(value = "/editProduct/{productId}")
    public String editProduct(@PathVariable(name="productId") String productId , Model model){
        Long id = Long.parseLong(productId);
        model.addAttribute("product" , service.get(id));
        model.addAttribute("productField" , new Product());
        return "Admin/editProduct";
    }

    @PostMapping(value = "/editProduct")
    public String adminEditProduct(@ModelAttribute Product product , Model model){
        System.out.println(product);
        service.updateProduct(product);
        return "redirect:/admin/dashboard";
    }

    @PostMapping(value = "/addProduct")
    public String addProduct(@ModelAttribute Product product){
        service.saveProduct(product);
        return "redirect:/admin/dashboard";
    }


    @PostMapping(value = "/deleteProduct/{productId}")
    public String deleteProduct(@PathVariable(name="productId") String productId){
        Long id = Long.parseLong(productId);
        service.deleteProductById(id);
        return "redirect:/admin/dashboard";
    }
}
