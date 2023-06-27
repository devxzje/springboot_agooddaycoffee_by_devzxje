package com.example.demo.repository;

import com.example.demo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("select customer from Customer customer where concat(customer.firstName,' ',customer.lastName,' ',customer.address,' ',customer.phoneNumber) like %?1% ")
    public List<Customer> filter(String keyword);
}
