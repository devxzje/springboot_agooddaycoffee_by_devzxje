package com.example.demo.service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Customer;

import java.util.List;

public interface CustomerService {

    public List<Customer> filter(String keyword);

    public Customer findById(Integer id) throws NotFoundException;
}
