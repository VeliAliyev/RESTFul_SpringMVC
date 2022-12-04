package com.velialiyev.restful_springmvc.services;

import com.velialiyev.restful_springmvc.api.v1.model.CategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryByName(String name);

}