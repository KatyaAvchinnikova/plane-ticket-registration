package com.example.auditlayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationPropertiesScan
public class AuditLayerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuditLayerApplication.class, args);
    }

}
