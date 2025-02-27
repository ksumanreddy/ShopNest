package com.example.shopnest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String title;
    private String description;
    private String imageURL;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Category category;

}
