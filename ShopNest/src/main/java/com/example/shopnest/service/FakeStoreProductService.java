package com.example.shopnest.service;

import com.example.shopnest.dto.FakeStoreResponseDTO;
import com.example.shopnest.model.Category;
import com.example.shopnest.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService {

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product getProductByid(Integer id) {
        Product product = new Product();
        ResponseEntity<FakeStoreResponseDTO> fakeStoreResponse =
        restTemplate.getForEntity("https://fakestoreapi.com/products/" + id, FakeStoreResponseDTO.class);
        FakeStoreResponseDTO response =fakeStoreResponse.getBody();
        if(response == null) {
            try {
                throw new IllegalAccessException("FakeStore APi no found");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        product = convertFakeStoreResponseToProduct(response);
        return product;
    }
    public List<Product> getAllProducts(){
        List<Product> response = new ArrayList<>();
        ResponseEntity<FakeStoreResponseDTO[]> fakeStoreProducts =
                restTemplate.getForEntity("https://fakestoreapi.com/products/", FakeStoreResponseDTO[].class);
        for(FakeStoreResponseDTO fakeStoreResponse : fakeStoreProducts.getBody()) {
            response.add(convertFakeStoreResponseToProduct(fakeStoreResponse));
        }
        return response;
    }
    public Product createProduct(String title, String imageURL,String description, String catTitle) {
        Product response = new Product();
        FakeStoreResponseDTO  requestBody = new FakeStoreResponseDTO();
        requestBody.setTitle(title);
        requestBody.setDescription(description);
        requestBody.setCategory(catTitle);
        requestBody.setImage(imageURL);
       FakeStoreResponseDTO fakeStoreResponse =
        restTemplate.postForObject("https://fakestoreapi.com/products", requestBody, FakeStoreResponseDTO.class);
        response = convertFakeStoreResponseToProduct(fakeStoreResponse);
       return response;
    }

   private Product convertFakeStoreResponseToProduct(FakeStoreResponseDTO response) {
        Product product = new Product();
       Category category = new Category();
       category.setTitle(response.getCategory());
       product.setId(response.getId());
       product.setCategory(category);
       product.setDescription(response.getDescription());
       product.setTitle(response.getTitle());
       product.setImageURL(response.getImage());
       return product;
   }

}
