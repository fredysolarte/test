package com.test.test.categorie;

import com.test.test.controllers.CategoryController;
import com.test.test.models.Category;
import com.test.test.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class CategoryControllerTest {
    private MockMvc mockMvc;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    void getCategoryId_ShouldReturnCategory() throws Exception {
        Category category = new Category();
        category.setId(1L);
        category.setNombre("Test Category");

        when(categoryService.getCategoryId(1)).thenReturn(category);

        mockMvc.perform(get("/categories/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.nombre", is("Test Category")));

        verify(categoryService, times(1)).getCategoryId(1);
    }

    @Test
    void createCategory_ShouldReturnCreatedCategory() throws Exception {
        Category category = new Category();
        category.setId(1L);
        category.setNombre("Test Category");

        when(categoryService.createCategory(any(Category.class))).thenReturn(category);

        mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Test Category\"}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.nombre", is("Test Category")));

        verify(categoryService, times(1)).createCategory(any(Category.class));
    }

    @Test
    void getAllCategories_ShouldReturnCategoryList() throws Exception {
        Category category = new Category();
        category.setId(1L);
        category.setNombre("Test Category");
        List<Category> categories = Collections.singletonList(category);

        when(categoryService.getAllCategories()).thenReturn(categories);

        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].nombre", is("Test Category")));

        verify(categoryService, times(1)).getAllCategories();
    }

    @Test
    void deleteCategory_ShouldCallService() throws Exception {
        mockMvc.perform(delete("/categories/1"))
                .andExpect(status().isOk());

        verify(categoryService, times(1)).deleteCategory(1);
    }
}
