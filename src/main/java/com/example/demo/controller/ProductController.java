package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/index")
    public String index(Model model,
                        @Param("keyword") String keyword) {
        List<Product> products = productService.filter(keyword);
        model.addAttribute("products", products);
        return "product/index";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("pageTitle", "Add some new");
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "product/form";
    }

    @PostMapping("/store")
    public String store(Product product,
                        RedirectAttributes redirectAttributes) {
        this.productRepository.save(product);
        redirectAttributes.addFlashAttribute("message", "Successfully");
        return "redirect:/product/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(RedirectAttributes redirectAttributes,
                         @PathVariable("id") Integer id) {
        this.productRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Successfully");
        return "redirect:/product/index";
    }

    @GetMapping("/update/{id}")
    public String update(Model model,
                         @PathVariable("id") Integer id,
                         RedirectAttributes redirectAttributes) {
        Optional<Product> product = productRepository.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("pageTitle", "Update");
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "product/form";
    }
}
