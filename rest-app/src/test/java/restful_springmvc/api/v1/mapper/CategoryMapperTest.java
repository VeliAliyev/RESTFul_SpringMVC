package restful_springmvc.api.v1.mapper;

import org.junit.jupiter.api.Test;
import restful_springmvc.api.v1.model.CategoryDto;
import restful_springmvc.domain.Category;

import static org.junit.jupiter.api.Assertions.assertEquals;

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