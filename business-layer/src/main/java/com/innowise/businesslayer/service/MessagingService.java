package com.innowise.businesslayer.service;

import com.innowise.businesslayer.domain.User;
import com.innowise.businesslayer.messaging.MessageProducer;
import com.innowise.message.AuditInfoMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessagingService {

    private final MessageProducer producer;

    public void send(User user) {
        AuditInfoMessage message = AuditInfoMessage.builder()
                                                   .birthDate(user.getBirthDate())
                                                   .email(user.getEmail())
                                                   .firstName(user.getFirstName())
                                                   .lastName(user.getLastName())
                                                   .userName(user.getUserName())
                                                   .build();
        producer.send(message);
    }

}
