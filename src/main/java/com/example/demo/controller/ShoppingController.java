package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/shopping")
public class ShoppingController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;


    @GetMapping("/index")
    public String index(Model model,
                        @Param("keyword") String keyword) {
        List<Product> products = productService.filter(keyword);
        model.addAttribute("products", products);
        return "shopping/index";
    }

}
