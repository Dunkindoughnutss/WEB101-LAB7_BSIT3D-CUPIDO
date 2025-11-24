package com.cupido.demo;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private Double price;


    public Product(Long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }


    public Product() {}
}