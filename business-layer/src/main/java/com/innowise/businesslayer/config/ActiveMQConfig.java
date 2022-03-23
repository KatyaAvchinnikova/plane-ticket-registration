package com.innowise.businesslayer.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

@Getter
@EnableJms
@Configuration
@AllArgsConstructor
public class ActiveMQConfig {
    @Bean
    public ConnectionFactory connectionFactory(){
        var factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL("tcp://localhost:61616");
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory());
        jmsTemplate.setPubSubNoLocal(true);
        return jmsTemplate;
    }

}
