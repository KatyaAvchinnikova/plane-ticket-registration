package com.innowise.businesslayer.service;

import com.innowise.businesslayer.domain.User;
import com.innowise.businesslayer.messaging.MessageProducer;
import com.innowise.message.AuditInfoMessage;
import com.innowise.message.EmailMessage;
import com.innowise.message.FtpInfoMessage;
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

    public void store(FtpInfoMessage message) {
        producer.store(message);
    }

    public void download(EmailMessage message) {
        producer.download(message);
    }

}
