package com.test.test.controllers;
import com.test.test.entities.Product;
import com.test.test.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.createProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody List<Product> products) {
        try {
            productService.createProducts(products);
            return ResponseEntity.ok("Productos creados exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear productos: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(Pageable pageable) {
        Page<Product> products = productService.getAllProducts(pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Page<Product>> getProductsByCategoryId(
            @PathVariable Long categoryId, Pageable pageable) {
        Page<Product> products = productService.getProductsByCategoryId(categoryId, pageable);
        return ResponseEntity.ok(products);
    }

}
