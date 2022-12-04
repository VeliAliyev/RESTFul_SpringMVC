package com.velialiyev.restful_springmvc.services.impl;

import com.velialiyev.restful_springmvc.api.v1.mapper.CategoryMapper;
import com.velialiyev.restful_springmvc.api.v1.model.CategoryDto;
import com.velialiyev.restful_springmvc.repository.CategoryRepository;
import com.velialiyev.restful_springmvc.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::categoryToCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryByName(String name) {
        return categoryMapper.categoryToCategoryDto(categoryRepository.findByName(name));
    }
}
