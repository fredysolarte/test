package com.test.test.controllers;
import com.test.test.models.Product;
import com.test.test.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.test.test.routes.InternalRoutes.*;

@RestController
@RequestMapping(PRODUCTS)
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return productService.getAllProducts(page, size);
    }

    @GetMapping(ID)
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        productService.createProduct(product);
        return product;
    }

    @GetMapping(SEARCH_ID)
    public Product getProductWithCategoryPhoto(@PathVariable Long id) {
        return productService.getProductWithCategoryPhoto(id);
    }
}