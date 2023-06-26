package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/index")
    public String index(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "category/index";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("pageTitle", "Add some new");
        return "category/form";
    }

    @PostMapping("/store")
    public String store(Category category,
                        RedirectAttributes redirectAttributes) {
        this.categoryRepository.save(category);
        redirectAttributes.addFlashAttribute("message", "Successfully");
        return "redirect:/category/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(RedirectAttributes redirectAttributes,
                         @PathVariable("id") Integer id) {
        this.categoryRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Successfully");
        return "redirect:/category/index";
    }

    @GetMapping("/update/{id}")
    public String update(Model model,
                         @PathVariable("id") Integer id,
                         RedirectAttributes redirectAttributes) {
        Optional<Category> category = categoryRepository.findById(id);
        model.addAttribute("category", category);
        model.addAttribute("pageTitle", "Update");
        return "category/form";


    }
}