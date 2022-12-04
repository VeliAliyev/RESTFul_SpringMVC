package com.velialiyev.restful_springmvc.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class CategoryListDto {
    List<CategoryDto> categories;
}
