package com.example.demo.controller;


import com.example.demo.exception.NotFoundException;
import com.example.demo.model.CartItem;
import com.example.demo.model.Customer;
import com.example.demo.model.Product;
import com.example.demo.model.ShoppingCart;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ShoppingCartRepository;
import com.example.demo.service.CustomerService;
import com.example.demo.service.ProductService;
import com.example.demo.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ShoppingCartService cartService;

    @Autowired
    ProductService productService;

    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public String add(RedirectAttributes redirectAttributes,
                      Model model,
                      @RequestParam("id") Integer productId,
                      @RequestParam(value = "quantity", required = false, defaultValue = "1") int quantity) throws NotFoundException {
        Customer customer = customerService.findById(1);
        Product product = productService.findById(productId);
        ShoppingCart cart = cartService.addToCart(product, quantity, customer);
        List<CartItem> cartItems = cartItemRepository.findAll();
        model.addAttribute("cartItems", cartItems);
        redirectAttributes.addFlashAttribute("message", "Add to cart successfully");
        return "redirect:/shopping/index";

    }

    @GetMapping("/view")
    public String view(Model model,
                       RedirectAttributes redirectAttributes) throws NotFoundException {
        Customer customer = customerService.findById(1);
        ShoppingCart cart = customer.getShoppingCart();
        if (cart == null) {
            model.addAttribute("message", "Nothing in your cart, go back to buy some coffee");
        } else {
            Set<CartItem> cartItems = cart.getCartItem();
            model.addAttribute("cartItems", cartItems);
            Double totalPrice = cart.getTotalPrice();
            model.addAttribute("subTotal", totalPrice);
        }
        return "cart/index";
    }

    @PostMapping("/update")
    public String update(Model model,
                         @RequestParam("quantity") int quantity,
                         @RequestParam("id") Integer productId,
                         RedirectAttributes redirectAttributes
    ) throws NotFoundException {

        Customer customer = customerService.findById(1);

        Product product = productService.findById(productId);

        ShoppingCart cart = cartService.updateItemInCart(product, quantity, customer);

        List<CartItem> cartItems = cartItemRepository.findAll();
        model.addAttribute("cartItems", cartItems);
        redirectAttributes.addFlashAttribute("message", "Update cart successfully");
        return "redirect:/cart/view";
    }

    @GetMapping("/delete/{id}")
    public String deleteItemFromCart(@PathVariable("id") Integer productId,
                                     Model model,
                                     RedirectAttributes redirectAttributes) throws NotFoundException {

        Customer customer = customerService.findById(1);

        Product product = productService.findById(productId);

        ShoppingCart cart = cartService.deleteItemFromCart(product, customer);

        List<CartItem> cartItems = cartItemRepository.findAll();

        model.addAttribute("cartItems", cartItems);
        redirectAttributes.addFlashAttribute("message", "Delete from cart successfully");
        return "redirect:/cart/view";
    }
}