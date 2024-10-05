package com.test.test.services;


import com.test.test.exceptios.UserException;
import com.test.test.models.Product;
import com.test.test.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.test.test.constants.Constants.ERROR_404;
import static com.test.test.constants.Constants.PRODUCT_NOT_FOUND;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public List<Product> getAllProducts(int page, int size) {
        return productRepository.getAllProducts(page, size);
    }

    public Product getProductById(Long id) {
        Product product = productRepository.getProductById(id);
        if (product == null) {
            throw new UserException(ERROR_404, PRODUCT_NOT_FOUND);
        }
        return product;
    }

    public void createProduct(Product product) {
        productRepository.insertProduct(product);
    }

    public void insertInBatch(List<Product> products) {
        productRepository.insertInBatch(products);
    }

    public Product getProductWithCategoryPhoto(Long id) {
        Product product = productRepository.getProductWithCategoryPhoto(id);
        if (product == null) {
            throw new UserException(ERROR_404, PRODUCT_NOT_FOUND);
        }
        return product;
    }
}