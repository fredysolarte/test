package com.test.test.product;

import com.test.test.exceptios.UserException;
import com.test.test.models.Product;
import com.test.test.repositories.ProductRepository;
import com.test.test.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductServiceTest {
    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    public ProductServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        Product product = new Product();
        product.setId(1L);
        product.setNombre("Product1");

        when(productRepository.getAllProducts()).thenReturn(Collections.singletonList(product));

        List<Product> result = productService.getAllProducts();

        assertEquals(1, result.size());
        assertEquals("Product1", result.get(0).getNombre());
    }

    @Test
    public void testGetProductByIdNotFound() {
        when(productRepository.getProductById(1L)).thenReturn(null);

        assertThrows(UserException.class, () -> productService.getProductById(1L));
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setNombre("Product1");

        doNothing().when(productRepository).insertProduct(any(Product.class));

        productService.createProduct(product);

        verify(productRepository).insertProduct(product);
    }
}
