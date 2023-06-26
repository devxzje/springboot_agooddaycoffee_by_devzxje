package com.example.demo.service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Product;

import java.util.List;

public interface ProductService {

    public List<Product> filter(String keyword);

    public Product findById(Integer id) throws NotFoundException;

}
