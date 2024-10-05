package com.test.test.services;

import com.test.test.exceptios.UserException;
import com.test.test.models.Category;
import com.test.test.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.test.test.constants.Constants.ERROR_500;
import static com.test.test.constants.Constants.GENERIC_ERROR;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category getCategoryId(int id) {
        Category category = categoryRepository.getCategoryId(id);
        if (category == null) {
            throw new UserException(ERROR_500, GENERIC_ERROR);
        }
        return category;
    }

    public Category createCategory(Category category) {
        return categoryRepository.createCategory(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.getAllCategories();
    }

    public void deleteCategory(int id) {
        categoryRepository.deleteCategory(id);
    }
}
