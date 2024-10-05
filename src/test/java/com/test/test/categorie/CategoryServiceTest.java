package com.test.test.categorie;

import com.test.test.exceptios.UserException;
import com.test.test.models.Category;
import com.test.test.repositories.CategoryRepository;
import com.test.test.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCategoryId_ShouldReturnCategory() {
        Category category = new Category();
        category.setId(1L);
        category.setNombre("Test Category");

        when(categoryRepository.getCategoryId(1)).thenReturn(category);

        Category result = categoryService.getCategoryId(1);
        assertNotNull(result);
        assertEquals("Test Category", result.getNombre());
        verify(categoryRepository, times(1)).getCategoryId(1);
    }

    @Test
    void getCategoryId_ShouldThrowUserException_WhenCategoryNotFound() {
        when(categoryRepository.getCategoryId(1)).thenReturn(null);

        UserException thrown = assertThrows(UserException.class, () -> categoryService.getCategoryId(1));

        verify(categoryRepository, times(1)).getCategoryId(1);
    }

    @Test
    void createCategory_ShouldReturnCreatedCategory() {
        Category category = new Category();
        category.setNombre("Test Category");

        when(categoryRepository.createCategory(any(Category.class))).thenReturn(category);

        Category result = categoryService.createCategory(category);
        assertNotNull(result);
        assertEquals("Test Category", result.getNombre());
        verify(categoryRepository, times(1)).createCategory(any(Category.class));
    }

    @Test
    void getAllCategories_ShouldReturnListOfCategories() {
        Category category = new Category();
        category.setId(1L);
        category.setNombre("Test Category");
        List<Category> categories = Collections.singletonList(category);

        when(categoryRepository.getAllCategories()).thenReturn(categories);

        List<Category> result = categoryService.getAllCategories();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Category", result.get(0).getNombre());
        verify(categoryRepository, times(1)).getAllCategories();
    }

    @Test
    void deleteCategory_ShouldCallRepositoryDelete() {
        categoryService.deleteCategory(1);
        verify(categoryRepository, times(1)).deleteCategory(1);
    }
}
