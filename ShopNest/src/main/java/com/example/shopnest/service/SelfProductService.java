package com.example.shopnest.service;

import com.example.shopnest.model.Category;
import com.example.shopnest.model.Product;
import com.example.shopnest.repository.CategoryRepo;
import com.example.shopnest.repository.ProductRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        Optional<Product> product = productRepo.findById(id);
        return product.orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
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
      Optional<Category> optionalCategory = categoryRepo.findByTitle(catTitle);
      if (optionalCategory.isPresent()) {
          category = optionalCategory.get();
      }
      else {
            category = new Category();
            category.getCreatedAt(new Date());
            category.getUpdatedAt(new Date());
            category.setTitle(catTitle);
            category = categoryRepo.save(category);
        }
      product.setCategory(category);
      Product response = productRepo.save(product);
      return response;
    }
    @Override
    public Product updateProduct(Integer id, String title, String imageURL, String description, String catTitle) {
        Product product = productRepo.findById(id).get();
        product.setTitle(title);
        product.setImageURL(imageURL);
        product.setDescription(description);
        product.getUpdatedAt( new Date());
        product.getCategory().setTitle(catTitle);
        Product response = productRepo.save(product);
        return response;
    }

    @Override
    public void deleteProductById(Integer id) {
        productRepo.deleteById(id);
    }
    @Override
    public Page<Product> getPaginatedProducts(int pageno, int pagesize){
        Pageable pageable = PageRequest.of(pageno, pagesize);

        String sortBy = "title";
     //   Pageable pageableSort = PageRequest.of(pageno, pagesize, Sort.Direction.ASC,sortBy);
        //** or
         Pageable pageableSort = PageRequest.of(pageno, pagesize, Sort.by("title").ascending());

        Page<Product> productPage=  productRepo.findAll(pageableSort);
        return productPage;
    }
}
