package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product", uniqueConstraints = @UniqueConstraint(columnNames = {"product_name", "product_image"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_description")
    private String description;

    @Column(name = "quanity")
    private Integer quanity;

    @Lob
    @Column(name = "product_image", columnDefinition = "MEDIUMLOB")
    private String image;

    @Column(name = "price")
    private Double price;

    @Column(name = "product_status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;

}
