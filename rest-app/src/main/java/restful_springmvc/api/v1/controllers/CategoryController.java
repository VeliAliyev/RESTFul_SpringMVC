package restful_springmvc.api.v1.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import restful_springmvc.api.v1.model.CategoryDto;
import restful_springmvc.api.v1.model.CategoryListDto;
import restful_springmvc.services.CategoryService;
import lombok.AllArgsConstructor;




@Controller
@RequestMapping("/api/v1/categories")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<CategoryListDto> getAllCategories(){
        return new ResponseEntity<>
                (new CategoryListDto(categoryService.getAllCategories()), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<CategoryDto> getCategoryByName(@PathVariable String name){
        return new ResponseEntity<>(categoryService.getCategoryByName(name), HttpStatus.OK);
    }
}