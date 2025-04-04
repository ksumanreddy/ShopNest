package com.example.shopnest.controller;

import com.example.shopnest.dto.CategoryRequestDTO;
import com.example.shopnest.dto.CreateProductRequestDTO;
import com.example.shopnest.model.Product;
import com.example.shopnest.service.ProductService;
import com.example.shopnest.service.SelfProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import com.example.shopnest.service.FakeStoreProductService;

import java.util.List;

@RestController
public class ProductController {
    private ProductService service;

    public ProductController(@Qualifier("selfProductService") ProductService inputService) {
        this.service = inputService;
    }
    @GetMapping("/products/{id}")
    @Cacheable(value ="product", key = "#id")
    public Product getProductById(@PathVariable("id") Integer id) throws IllegalAccessException {
        if(id == null) {
            throw new IllegalAccessException("Id cannot be null");
        }
        return service.getProductByid(id);
    }

    //Get all products
    @GetMapping("/products")
    @Cacheable(value = "products")
    public List<Product> getAllProducts(){
        //S1 : Any Validations
        //Step 2 : Call the service layer
        List<Product> products = service.getAllProducts();
        return products;
    }

    @GetMapping("/products/{pageno}/{pagesize}")
    @Cacheable(value = "products", key = "'page_' + #pageno + '_size_' + #pagesize")
    public Page<Product> getPaginatedProducts(@PathVariable("pageno") int pageno,
                                              @PathVariable("pagesize") int pagesize){
       return service.getPaginatedProducts(pageno, pagesize);
    }

    //  Create a product
    @PostMapping("/products")
    @CachePut(value ="product", key = "#result.id")
    public Product createProduct(@RequestBody CreateProductRequestDTO request) {
        return service.createProduct(request.getTitle(),request.getImageURL(),request.getDescription(),request.getCategory().getTitle());
    }
    ///  update product
    @PutMapping("/product/{id}")
    @CachePut(value = "product", key = "#id")
    public Product updateProduct(@PathVariable("id") Integer id, @RequestBody CreateProductRequestDTO request) {
        //Integer id, String title, String imageURL, String description, String catTitle
      return service.updateProduct(id,request.getTitle(),request.getImageURL(),request.getDescription(),request.getCategory().getTitle());
    }
    //delete a product by id
    @DeleteMapping("/product/{id}")
    @CacheEvict(value = "product", key = "#id") // Remove Product from cache
    public void deleteProduct(@PathVariable("id") Integer id) {
       service.deleteProductById(id);
    }
}
