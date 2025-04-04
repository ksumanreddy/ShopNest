package com.example.shopnest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel implements Serializable {
    private String title;
    @OneToMany(mappedBy = "category", fetch=FetchType.EAGER)
    @JsonIgnore
    private List<Product> products;
}
