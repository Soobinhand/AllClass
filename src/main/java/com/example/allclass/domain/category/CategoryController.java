package com.example.allclass.domain.category;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CategoryController {
    private CategoryRepository categoryRepository;
    private CategoryDto categoryDto;
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService, CategoryRepository categoryRepository){
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/category/{categoryId}")
    public String sat(@PathVariable int categoryId) throws Exception {
        Category category = categoryService.getCategory(categoryId); // DB 로 가는 길...?
        System.out.println(category.getCategoryName());
        return "sat";
    }
}
