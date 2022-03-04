package com.sagarthyme.brs.service.category;

import com.sagarthyme.brs.dto.CategoryDto;
import com.sagarthyme.brs.model.Category;
import com.sagarthyme.brs.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    public CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> findAll() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream().map(category -> CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription()).build()).collect(Collectors.toList());
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        Category entity = Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .description(categoryDto.getDescription()).build();
        entity=categoryRepository.save(entity);
        return CategoryDto.builder()
                .id(entity.getId()).build();
    }

    @Override
    public void deleteById(Integer integer) {
        categoryRepository.deleteById(integer);
    }

    @Override
    public CategoryDto findById(Integer integer) {
        Optional<Category> optionalCategory = categoryRepository.findById(integer);
        if (optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            return CategoryDto.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .description(category.getDescription()).build();
        }
        return null;
    }
}
