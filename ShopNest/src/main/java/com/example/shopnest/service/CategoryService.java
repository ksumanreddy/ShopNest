package com.example.shopnest.service;

import com.example.shopnest.model.Category;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public interface CategoryService {
    public Category getCategoryById(Integer id);
    public List<Category> getAllCategories();
    public Category addCategory(String title);
    public Category updateCategory(Integer id, String title);
    public void deleteCategoryById(Integer id);
}
