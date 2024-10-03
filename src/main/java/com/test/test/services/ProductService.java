package com.test.test.services;

import com.test.test.entities.Product;
import com.test.test.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Page<Product> getProductsByCategoryId(Long categoriaId, Pageable pageable) {
        return productRepository.findByCategoriaId(categoriaId, pageable);
    }

    @Transactional
    public void createProducts(List<Product> products) {
        productRepository.saveAll(products);
    }
}
