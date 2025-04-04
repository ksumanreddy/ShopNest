package com.example.shopnest.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) ///  Auto Increment part
    private Integer id;
    private Date createdAt;
    private Date updatedAt;
    private boolean isDeleted;

    public void getUpdatedAt(Date date) {
        this.updatedAt = date;
    }

    public void getCreatedAt(Date date) {
        this.createdAt = date;
    }
}
