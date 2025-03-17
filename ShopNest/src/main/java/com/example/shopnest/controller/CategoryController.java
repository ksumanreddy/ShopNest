package com.example.shopnest.controller;

import com.example.shopnest.dto.CategoryRequestDTO;
import com.example.shopnest.model.Category;
import com.example.shopnest.service.CategoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    private CategoryService service;

    public CategoryController(@Qualifier("selfCategoryService") CategoryService inputService) {
        this.service = inputService;
    }
    @GetMapping("/category/{id}")
    public Category getCategoryById(@PathVariable("id") Integer id) {
        return service.getCategoryById(id);
    }

    @GetMapping("category/")
    public List<Category> getAllCategories() {
        return service.getAllCategories();
    }
    @PostMapping("category/")
    public Category addCategory(@RequestBody CategoryRequestDTO request) {
        return service.addCategory(request.getTitle());
    }
    @PutMapping("category/{id}")
    public Category updateCategory(@PathVariable("id") Integer id, @RequestBody CategoryRequestDTO request) {
        return service.updateCategory(id,request.getTitle());
    }
    @DeleteMapping("category/{id}")
    public void deleteCategory(@PathVariable("id") Integer id) {
        service.deleteCategoryById(id);
    }
}
