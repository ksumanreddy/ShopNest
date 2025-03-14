package com.example.shopnest.repository;

import com.example.shopnest.model.Category;
import com.example.shopnest.model.Product;
import com.example.shopnest.repository.projections.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    Optional<List<Product>> findAllByCategoryId(Integer id);

    Product save(Product product);

    void deleteByid(Integer id);

    void deleteByCategory(Category category);

    void deleteByidAndCategory(Integer id, Category category);

    /**
     * HQL query
     * UseCase : GetProductNameByTitle
     */
    @Query("select p.title from Product p where p.title= : title")
    ProductProjection getProductNameByTitle(@Param("title") String title);

    @Query("select p from Product p where p.title=:title AND p.id =: id")
    Product getProductNameByTitleAndId(@Param("title") String title, @Param("id") Integer id);
}
