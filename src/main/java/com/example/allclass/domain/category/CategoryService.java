package com.example.allclass.domain.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    private CategoryDto categoryDto;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    public Category getCategory(int categoryId)throws Exception{
        Optional<Category> categoryOptional
                = categoryRepository.findById(categoryId);
        if (categoryOptional.isEmpty()){
            throw new Exception("***error!!!!****");
        }
        return categoryOptional.get();
    }
}
