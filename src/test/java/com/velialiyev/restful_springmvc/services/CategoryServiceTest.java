package com.velialiyev.restful_springmvc.services;

import com.velialiyev.restful_springmvc.api.v1.mapper.CategoryMapper;
import com.velialiyev.restful_springmvc.api.v1.model.CategoryDto;
import com.velialiyev.restful_springmvc.domain.Category;
import com.velialiyev.restful_springmvc.repository.CategoryRepository;
import com.velialiyev.restful_springmvc.services.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CategoryServiceTest {

    @Mock
    CategoryRepository categoryRepository;

    CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository, CategoryMapper.INSTANCE);
    }

    @Test
    void getAllCategories() {
        //given
        List<Category> categories = new ArrayList<>(Arrays.asList(
                Category.builder().id(1L).name("Fresh").build(),
                Category.builder().id(2L).name("Nuts").build()
        ));
        when(categoryRepository.findAll()).thenReturn(categories);

        //when
        List<CategoryDto> categoryList = categoryService.getAllCategories();

        //then
        assertEquals(2, categoryList.size());
        assertEquals(1L, categoryList.get(0).getId());
        assertEquals("Fresh", categoryList.get(0).getName());
        assertEquals(2L, categoryList.get(1).getId());
        assertEquals("Nuts", categoryList.get(1).getName());
    }

    @Test
    void getCategoryByName() {

        //given
        Category category = new Category(1L, "Nuts");
        when(categoryRepository.findByName(anyString())).thenReturn(category);

        //when
        CategoryDto categoryDto = categoryService.getCategoryByName("Nuts");

        //then
        assertEquals(1L, categoryDto.getId());
        assertEquals("Nuts", categoryDto.getName());
    }
}