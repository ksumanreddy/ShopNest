package com.example.shopnest.repository;

import com.example.shopnest.model.Category;
import com.example.shopnest.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    // Select * from products where id = id
    Optional<Product> findByid(Integer id);
    //
    Optional<Product> findByCategory(Category category);

    Optional<Product> findByIdAndCategory(Integer id, Category category);


    Optional<List<Product>> findAllByCategory(Category category);

    Product save(Product product);

    void deleteByid(Integer id);

    void deleteByCategory(Category category);

    void deleteByidAndCategory(Integer id, Category category);
}
