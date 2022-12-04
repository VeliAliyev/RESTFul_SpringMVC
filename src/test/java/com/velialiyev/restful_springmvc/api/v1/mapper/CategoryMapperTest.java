package com.velialiyev.restful_springmvc.api.v1.mapper;

import com.velialiyev.restful_springmvc.api.v1.model.CategoryDto;
import com.velialiyev.restful_springmvc.domain.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    CategoryMapper mapper = CategoryMapper.INSTANCE;

    @Test
    void categoryToCategoryDto() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Veli");
        CategoryDto dto = mapper.categoryToCategoryDto(category);
        assertEquals(1L, dto.getId());
        assertEquals("Veli", dto.getName());
    }
}