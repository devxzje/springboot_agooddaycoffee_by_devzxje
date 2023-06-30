package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;
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
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/index")
    public String index(Model model,
                        @Param("keyword") String keyword) {
        List<Customer> customers = customerService.filter(keyword);
        model.addAttribute("customers", customers);
        model.addAttribute("keyword", keyword);
        return "customer/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(RedirectAttributes redirectAttributes,
                         @PathVariable("id") Integer id) {
        customerRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "The customer has been deleted successfully.");
        return "redirect:/customer/index";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("pageTitle", "Add New Custumer");
        return "customer/form";
    }

    @PostMapping("/store")
    public String store(Customer customer, RedirectAttributes redirectAttributes) {
        customerRepository.save(customer);
        redirectAttributes.addFlashAttribute("message", "The customer has been saved successfully.");
        return "redirect:/customer/index";
    }


    @GetMapping("/update/{id}")
    public String update(Model model,
                         @PathVariable("id") Integer id,
                         RedirectAttributes redirectAttributes
    ) {

        Optional<Customer> customer = customerRepository.findById(id);
        model.addAttribute("customer", customer);
        model.addAttribute("pageTitle", "Update customer");
        return "customer/form";


    }
}
