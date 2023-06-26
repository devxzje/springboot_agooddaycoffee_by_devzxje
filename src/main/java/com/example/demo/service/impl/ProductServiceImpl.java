package com.example.demo.service.impl;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> filter(String keyword) {
        if (keyword != null) {
            return productRepository.filter(keyword);
        }
        return productRepository.findAll();
    }

    @Override
    public Product findById(Integer id) throws NotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        throw new NotFoundException("Can not found product with id:" + id);
    }


}
