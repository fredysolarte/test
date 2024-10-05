package com.test.test.product;

import com.test.test.controllers.ProductController;
import com.test.test.models.Product;
import com.test.test.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductControllerTest {
    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    public ProductControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setNombre("Product1");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setNombre("Product2");

        when(productService.getAllProducts(0, 10)).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productController.getAllProducts(0, 10);

        assertEquals(2, products.size());
        assertEquals("Product1", products.get(0).getNombre());
        assertEquals("Product2", products.get(1).getNombre());
        verify(productService).getAllProducts(0, 10);
    }

    @Test
    public void testGetProductById() {
        Product product = new Product();
        product.setId(1L);
        product.setNombre("Product1");

        when(productService.getProductById(1L)).thenReturn(product);

        Product result = productController.getProductById(1L);

        assertEquals("Product1", result.getNombre());
        verify(productService).getProductById(1L);
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setNombre("Product1");

        doNothing().when(productService).createProduct(any(Product.class));

        Product result = productController.createProduct(product);

        assertEquals("Product1", result.getNombre());
        verify(productService).createProduct(any(Product.class));
    }

    @Test
    public void testGetProductWithCategoryPhoto() {
        Product product = new Product();
        product.setId(1L);
        product.setNombre("Product1");

        when(productService.getProductWithCategoryPhoto(1L)).thenReturn(product);

        Product result = productController.getProductWithCategoryPhoto(1L);

        assertEquals("Product1", result.getNombre());
        verify(productService).getProductWithCategoryPhoto(1L);
    }
}
