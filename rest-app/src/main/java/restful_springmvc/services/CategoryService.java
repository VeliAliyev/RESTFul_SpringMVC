package restful_springmvc.services;

import restful_springmvc.api.v1.model.CategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryByName(String name);
}
