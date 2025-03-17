package com.example.shopnest.service;

import com.example.shopnest.model.Category;
import com.example.shopnest.model.Product;
import com.example.shopnest.repository.CategoryRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("selfCategoryService")
public class SelfCategoryService implements CategoryService {
    CategoryRepo categoryRepo;
    public SelfCategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }
    @Override
    public Category getCategoryById(Integer id) {
        Category category = new Category();
        category = categoryRepo.findById(id).get();
        return category;
    }

    @Override
    public List<Category> getAllCategories(){
        List<Category> categories = categoryRepo.findAll();
        return categories;
    }
    @Override
    public Category addCategory(String title) {
        Category category = new Category();
        category.setTitle(title);
        category.getCreatedAt(new Date());
        category.getUpdatedAt(new Date());
        category = categoryRepo.save(category);
        return category;
    }

    @Override
    public Category updateCategory(Integer id, String title){
        Category category = new Category();
        category.setId(id);
        category.setTitle(title);
        category = categoryRepo.save(category);
        return category;
    }
    @Override
    public void deleteCategoryById(Integer id){
       categoryRepo.deleteById(id);
    }
}
