package com.test.test.controllers;
import com.test.test.models.Category;
import com.test.test.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static com.test.test.routes.InternalRoutes.CATEGORIES;
import static com.test.test.routes.InternalRoutes.ID;

@RestController
@RequestMapping(CATEGORIES)
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping(ID)
    public Category getCategoryId(@PathVariable int id) {
        return categoryService.getCategoryId(id);
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @DeleteMapping(ID)
    public void deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
    }
}
