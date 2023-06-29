package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.model.Product;
import com.example.demo.model.ShoppingCart;

public interface ShoppingCartService {

    ShoppingCart addToCart(Product product, Integer quantity, Customer customer);

    ShoppingCart updateItemInCart(Product product, int quantity, Customer customer);

    ShoppingCart deleteItemFromCart(Product product, Customer customer);
}
