package com.example.shopnest.repository;

import com.example.shopnest.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
    Optional<Category> findByTitle(String catTitle);
    Optional<Category>  findById(Integer id);
}
