package com.innowise.businesslayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableCaching
@EnableJms
@EnableFeignClients
@EnableDiscoveryClient
public class BusinessLayerNewApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinessLayerNewApplication.class, args);
    }

}