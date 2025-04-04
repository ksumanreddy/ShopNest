package com.example.shopnest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends BaseModel implements Serializable {
    private String title;
    private String description;
    private String imageURL;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Category category;

}
