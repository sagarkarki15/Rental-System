package com.sagarthyme.brs.service.category;


import com.sagarthyme.brs.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> findAll();
    CategoryDto save(CategoryDto categoryDto);
    void deleteById(Integer integer);
    CategoryDto findById(Integer integer);
}
