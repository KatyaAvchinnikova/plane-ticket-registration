package com.example.auditlayer.messaging;

import com.example.auditlayer.service.MessageService;
import com.example.message.AuditInfoMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@Component
@Slf4j
@RequiredArgsConstructor
public class MessageConsumer implements MessageListener {
    private final MessageService messageService;

    @Override
    public void onMessage(Message message) {
        try {
            log.info("consuming...");
            ObjectMessage objectMessage = (ObjectMessage) message;
            AuditInfoMessage auditInfoMessage = (AuditInfoMessage) objectMessage.getObject();
            messageService.save(auditInfoMessage);
        } catch (Exception e) {
            log.error("Receiving exception: " + Arrays.toString(e.getStackTrace()));
        }
    }

}
