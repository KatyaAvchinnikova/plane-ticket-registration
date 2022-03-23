package com.innowise.auditlayer.messaging;

import com.innowise.auditlayer.service.MessageService;
import com.innowise.message.AuditInfoMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@Component
@Slf4j
@RequiredArgsConstructor
public class MessageConsumer implements MessageListener {
    private final MessageService messageService;

    @Override
    @JmsListener(destination = "queue1")
    public void onMessage(Message message) {
        try {
            log.info("consuming...message: " + message.toString());
            ObjectMessage objectMessage = (ObjectMessage) message;
            AuditInfoMessage auditInfoMessage = (AuditInfoMessage) objectMessage.getObject();
            //LocalDate birthDate = LocalDate.of(auditInfoMessage.);
            messageService.save(auditInfoMessage);
        } catch (Exception e) {
            log.error("Receiving exception: " + Arrays.toString(e.getStackTrace()));
        }

    }

}
