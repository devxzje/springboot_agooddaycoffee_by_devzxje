package com.example.demo.service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Order;
import com.example.demo.model.ShoppingCart;

public interface OrderService {

    public void save(ShoppingCart shoppingCart);

    Order acceptOrder(Integer id) ;

    void cancelOrder(Integer id);

    public Order findById(Integer id) throws NotFoundException;
}
