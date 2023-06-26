package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("select prod from Product  prod where concat(prod.category.name, ' ', prod.name, ' ') like %?1% ")
    public List<Product> filter(String keyword);


    @Query("select prod from Product  prod where concat(prod.category.name, ' ', prod.name, ' ') like %?1% ")
    Page<Product> searchProducts(String keyword, Pageable pageable);
}
