package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "shopping_cart")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "total_item")
    private Integer totalItem;

    @Column(name = "total_price")
    private Double totalPrice;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    private Set<CartItem> cartItem;

}

