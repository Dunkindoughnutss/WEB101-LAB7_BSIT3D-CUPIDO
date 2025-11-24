package com.cupido.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Optional;

// The import assumes ProductService is now in com.cupido.demo


@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 1. READ ALL Products (GET /api/products)
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        // Renamed service method: findAll()
        List<Product> allProducts = productService.findAll();
        return ResponseEntity.ok(allProducts);
    }

    // 2. READ ONE Product (GET /api/products/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        // Renamed service method: findById(id)
        Optional<Product> product = productService.findById(id);

        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 3. CREATE a new Product (POST /api/products)
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product newProduct) {
        // Renamed service method: save(newProduct)
        Product createdProduct = productService.save(newProduct);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdProduct.getId())
                .toUri();

        return ResponseEntity.created(location).body(createdProduct);
    }

    // 4. UPDATE an existing Product (PUT /api/products/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestBody Product productDetails)
    {
        // Service method signature fixed to return Optional<Product>
        Optional<Product> updatedProduct = productService.update(id, productDetails);

        if (updatedProduct.isPresent()) {
            return ResponseEntity.ok(updatedProduct.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 5. DELETE a Product (DELETE /api/products/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        // Renamed service method: deleteById(id)
        boolean wasDeleted = productService.deleteById(id);

        if (wasDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}