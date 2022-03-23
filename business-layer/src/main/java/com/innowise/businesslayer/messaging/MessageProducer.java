package com.innowise.businesslayer.messaging;

import com.innowise.message.AuditInfoMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageProducer {
    private final JmsTemplate jmsTemplate;

    public void send(AuditInfoMessage message){
        log.info("Sending...");
        jmsTemplate.convertAndSend("queue1", message);
        log.info(message.getUserName());
    }

}
