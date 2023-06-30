package com.example.demo.controller;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.CartItem;
import com.example.demo.model.Customer;
import com.example.demo.model.Order;
import com.example.demo.model.ShoppingCart;
import com.example.demo.repository.*;
import com.example.demo.service.CustomerService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import com.example.demo.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

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

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/check-out")
    public String checkout(Model model,
                           RedirectAttributes redirectAttributes) throws NotFoundException {
        List<CartItem> cartItems = cartItemRepository.findAll();
        model.addAttribute("cartItems", cartItems);
        Customer customer = customerService.findById(1);
        ShoppingCart cart = customer.getShoppingCart();
        Double totalPrice = cart.getTotalPrice();
        Integer totalItems = cart.getTotalItem();
        model.addAttribute("subTotal", totalPrice);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("customer", customer);
        return "order/form";
    }

    @PostMapping("/save")
    public String save1(Model model) throws NotFoundException {
        Customer customer = customerService.findById(1);
        ShoppingCart cart = customer.getShoppingCart();
        orderService.save(cart);
        List<Order> orders = customer.getOrders();
        model.addAttribute("orders", orders);
        return "redirect:/order/view";
    }

    @GetMapping("/view")
    public String view(Model model,
                       RedirectAttributes redirectAttributes) throws NotFoundException {
        Customer customer = customerService.findById(1);
        List<Order> orders = customer.getOrders();
        model.addAttribute("orders", orders);
        return "order/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model,
                         RedirectAttributes redirectAttributes,
                         @PathVariable("id") Integer id) throws NotFoundException {
        orderRepository.deleteById(id);
        return "redirect:/order/view";

    }

    @GetMapping("/management")
    public String management(Model model,
                       RedirectAttributes redirectAttributes) throws NotFoundException {
//        Customer customer = customerService.findById(1);
//        List<Order> orders = customer.getOrders();
        List<Order> orders= orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "order/management";
    }

    @GetMapping("/confirm/{id}")
    public String confirm(Model model,
                         RedirectAttributes redirectAttributes,
                         @PathVariable("id") Integer id) throws NotFoundException {
      Order order= orderService.findById(id);
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        order.setDeliveryDate(sqlDate);
        order.setOrderStatus(1);
        orderRepository.save(order);
        return "redirect:/order/management";
    }


}
