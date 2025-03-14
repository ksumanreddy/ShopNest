package com.example.shopnest.service;

import com.example.shopnest.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Product getProductByid(Integer id);
    List<Product> getAllProducts();
    Product createProduct(String title, String imageURL,String description, String catTitle);
}
