package com.fortunate.week7tasknfotech.controller;

import com.fortunate.week7tasknfotech.model.Product;
import com.fortunate.week7tasknfotech.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/allProduct")
    public String productPage(Model model) {
        List<Product> listProducts = service.listAllProduct();
        model.addAttribute("listProducts", listProducts);
        return "allProduct";
    }

    @GetMapping("/new")
    public String showNewProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "newProduct";
    }
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product")Product newProduct,
                              @RequestParam("productImage") MultipartFile multipartFile,
                              RedirectAttributes attributes) throws IOException {
        service.saveProductWithImage(multipartFile, newProduct);
        return "redirect:/allProduct";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showNewProductForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("editProduct");
        //mav.setViewName("editProduct"); VIEW NAME IS PASSED DIRECTLY ABOVE
        mav.addObject("product", service.get(id));
        return mav;
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id) {
        service.deleteProductById(id);
        return "redirect:/allProduct";
    }
}
