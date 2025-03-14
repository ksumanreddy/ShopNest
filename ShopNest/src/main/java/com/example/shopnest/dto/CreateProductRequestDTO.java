package com.example.shopnest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequestDTO {
    private String title;
    private String imageURL;
    private String description;
    private CategoryRequestDTO category;
}
