package com.happiness.membread.contexts.study.domain.service;

import com.happiness.membread.contexts.study.database.entities.Category;
import com.happiness.membread.contexts.study.database.repositories.CategoryRepository;
import com.happiness.membread.contexts.study.domain.dtos.CreateCategoryRequestDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class CategoryService {
    CategoryRepository categoryRepository;

    public Object createCategory(CreateCategoryRequestDto categoryRequestDto){
        Category category = new Category();
        category.setName(categoryRequestDto.getName());
        category.setDescription(categoryRequestDto.getDescription());

        return categoryRepository.save(category);
    }

    public Object getAllCategory(){
        return null;
    }

    public Category getCategory(int id){
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()){
            throw new RuntimeException("Not found category");
        }

        return category.get();
    }

    public Set<Category> getCategoriesOfCourse(String id){
        return categoryRepository.getCategoryOfCourse(id);
    }
}
