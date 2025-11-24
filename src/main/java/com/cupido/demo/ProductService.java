package com.cupido.demo;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProductService {

    private Map<Long, Product> products = new HashMap<>();
    private Long nextId = 4L;

    // dapat may constructor kana nga daan
    // public Product(Long id, String name, Double price) { ... }
    public ProductService() {
        // Note: I assume 'Product' natin is adto sa com.cupido.demo package
        products.put(1L, new Product(1L, "selpon", 5999.0));
        products.put(2L, new Product(2L, "iphonepromax", 2499.0));
        products.put(3L, new Product(3L, "router", 4500.0));
    }

    // Matches controller: productService.findAll()
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    // Matches controller: productService.findById(id) and returns Optional
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    // Matches controller: productService.save(p)
    public Product save(Product p) {
        p.setId(nextId++);
        products.put(p.getId(), p);
        return p;
    }


    public Optional<Product> update(Long id, Product updated) {
        if (!products.containsKey(id)) {
            return Optional.empty(); // didi mabalik kung wara aktibo
        }

        updated.setId(id);
        products.put(id, updated);
        return Optional.of(updated);
    }


    public boolean deleteById(Long id) {
        return products.remove(id) != null;
    }
}