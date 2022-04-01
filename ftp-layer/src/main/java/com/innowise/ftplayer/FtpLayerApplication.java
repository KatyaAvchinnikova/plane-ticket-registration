package com.innowise.ftplayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationPropertiesScan
@EnableEurekaClient
public class FtpLayerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FtpLayerApplication.class, args);
    }

}
