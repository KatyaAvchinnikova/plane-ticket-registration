package com.innowise.businesslayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableCaching
@EnableJms
@EnableFeignClients
@EnableEurekaClient
public class BusinessLayerNewApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinessLayerNewApplication.class, args);
    }

}