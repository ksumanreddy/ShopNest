package com.example.shopnest.controller;

import com.example.shopnest.dto.CategoryRequestDTO;
import com.example.shopnest.model.Category;
import com.example.shopnest.service.CategoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    private CategoryService service;

    public CategoryController(@Qualifier("selfCategoryService") CategoryService inputService) {
        this.service = inputService;
    }
    @GetMapping("/category/{id}")
    @Cacheable(value ="category",key="#id")
    public Category getCategoryById(@PathVariable("id") Integer id) {
        return service.getCategoryById(id);
    }

    @GetMapping("category/")
    @Cacheable(value ="category")
    public List<Category> getAllCategories() {
        return service.getAllCategories();
    }

    @GetMapping("category/{pageNo}/{pageSize}")
    @Cacheable(value = "category", key = "'page_' + #pageno + '_size_' + #pagesize")
    public Page<Category> getPaginatedCategory(@PathVariable("pageNo") int pageNo,
                                     @PathVariable("pageSize") int pageSize) {
        return service.getPaginatedCategory(pageNo,pageSize);
    }

    @PostMapping("category/")
    @CachePut(value = "category",key="#result.id")
    public Category addCategory( @RequestBody CategoryRequestDTO request) {
        return service.addCategory(request.getTitle());
    }
    @PutMapping("category/{id}")
    @CachePut(value = "category",key="#id")
    public Category updateCategory(@PathVariable("id") Integer id, @RequestBody CategoryRequestDTO request) {
        return service.updateCategory(id,request.getTitle());
    }
    @DeleteMapping("category/{id}")
    @CacheEvict(value = "category",key="#id")
    public void deleteCategory(@PathVariable("id") Integer id) {
        service.deleteCategoryById(id);
    }
}
