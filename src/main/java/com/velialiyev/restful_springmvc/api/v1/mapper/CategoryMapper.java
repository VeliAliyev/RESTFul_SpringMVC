package com.velialiyev.restful_springmvc.api.v1.mapper;

import com.velialiyev.restful_springmvc.api.v1.model.CategoryDto;
import com.velialiyev.restful_springmvc.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDto categoryToCategoryDto(Category category);
}
