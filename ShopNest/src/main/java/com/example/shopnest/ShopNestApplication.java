package com.example.shopnest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ShopNestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopNestApplication.class, args);
    }

}
