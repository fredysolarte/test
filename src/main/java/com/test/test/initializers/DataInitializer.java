package com.test.test.initializers;

import com.test.test.models.Category;
import com.test.test.models.Product;
import com.test.test.services.CategoryService;
import com.test.test.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.test.test.constants.Constants.PRODUCT;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    private final Random random = new Random();

    @Override
    public void run(String... args) throws Exception {
        List<Product> products = new ArrayList<>();
        List<Category> categories = categoryService.getAllCategories();

        if (categories.isEmpty()) {
            throw new IllegalStateException("No hay categorías disponibles para asignar a los productos.");
        }

        for (int i = 1; i <= 100000; i++) {
            Product product = new Product();
            product.setNombre(PRODUCT + i);
            product.setSuplierId((long) random.nextInt(100));
            product.setCategoriaId(categories.get(random.nextInt(categories.size())).getId());
            product.setCantidad(random.nextInt(1000));
            product.setPrecio(random.nextDouble() * 100);
            product.setUnidadStock(random.nextInt(100));
            product.setUnidadesOrden(random.nextInt(10));
            products.add(product);
        }

        productService.insertInBatch(products);
    }
}
