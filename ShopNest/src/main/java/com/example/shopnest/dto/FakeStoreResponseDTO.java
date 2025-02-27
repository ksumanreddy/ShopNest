package com.example.shopnest.dto;

import lombok.Getter;
import lombok.Setter;
import com.example.shopnest.model.Category;

@Getter
@Setter
public class FakeStoreResponseDTO {
    private Integer id;
    private String title;
    private String category;
    private String description;
    private String image;
}
