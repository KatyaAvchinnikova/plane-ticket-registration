package com.example.businesslayernew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BusinessLayerNewApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinessLayerNewApplication.class, args);
    }

}
