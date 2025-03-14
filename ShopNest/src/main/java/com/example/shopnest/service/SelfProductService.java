package com.example.shopnest.service;

import com.example.shopnest.model.Category;
import com.example.shopnest.model.Product;
import com.example.shopnest.repository.CategoryRepo;
import com.example.shopnest.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("selfProductService")
public class SelfProductService implements ProductService {
    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;

    public SelfProductService(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }
    @Override
    public Product getProductByid(Integer id) {
        // select * from products where id = inuputId
        Product product= productRepo.findById(id).get();
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll()ZZ;
    }

    @Override
    public Product createProduct(String title, String imageURL, String description, String catTitle) {
      Product product = new Product();
      Category category = new Category();
      product.setTitle(title);
      product.setImageURL(imageURL);
      product.setDescription(description);
      product.getCreatedAt( new Date());
      product.getUpdatedAt( new Date());
      Category existingCategory = categoryRepo.findByTitle(catTitle).get();
      if (existingCategory == null) {
          category.setTitle(catTitle);
//          category = categoryRepo.save(category);
      }
      product.setCategory(category);
      Product response = productRepo.save(product);
      return response;
    }
}
