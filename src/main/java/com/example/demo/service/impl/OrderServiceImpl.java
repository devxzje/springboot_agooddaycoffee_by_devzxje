package com.example.demo.service.impl;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.CartItem;
import com.example.demo.model.Order;
import com.example.demo.model.OrderDetail;
import com.example.demo.model.ShoppingCart;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ShoppingCartRepository;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CartItemRepository itemRepository;

    @Autowired
    private ShoppingCartRepository cartRepository;

    @Autowired
    OrderDetailRepository detailRepository;

    @Autowired
    OrderRepository orderRepository;


    @Override
    public void save(ShoppingCart shoppingCart) {
        Order order = new Order();
        java.util.Date utilDate = new java.util.Date();
        Date sqlDate = new Date(utilDate.getTime());
        order.setOrderDate(sqlDate);
        order.setCustomer(shoppingCart.getCustomer());
        order.setTotalPrice(shoppingCart.getTotalPrice());
        order.setOrderStatus(0);
        order.setTotalPrice(shoppingCart.getTotalPrice());
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (CartItem item : shoppingCart.getCartItem()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(item.getProduct());
            orderDetail.setQuantity(item.getQuantity());
            orderDetail.setTotalPrice(item.getTotalPrice());
            detailRepository.save(orderDetail);
            orderDetailList.add(orderDetail);
        }
        order.setOrderDetailList(orderDetailList);
        cartRepository.deleteById(shoppingCart.getId());
        orderRepository.save(order);
    }

    @Override
    public Order acceptOrder(Integer id) {
        Order order = orderRepository.getById(id);
        java.util.Date utilDate = new java.util.Date();
        Date sqlDate = new Date(utilDate.getTime());
        order.setDeliveryDate(sqlDate);
        return orderRepository.save(order);
    }

    @Override
    public void cancelOrder(Integer id) {

    }

    @Override
    public Order findById(Integer id) throws NotFoundException {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return order.get();
        }
        throw new NotFoundException("Can not found product with id:" + id);
    }
}
