package com.innowise.businesslayer.messaging;

import com.innowise.message.FtpInfoMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@Component
@Slf4j
@RequiredArgsConstructor
public class MessageConsumer implements MessageListener {

    @Override
    @JmsListener(destination = "queue4")
    public void onMessage(Message message) {
        try {
            log.info("consuming...message: " + message.toString());
            ObjectMessage objectMessage = (ObjectMessage) message;
            FtpInfoMessage auditInfoMessage = (FtpInfoMessage) objectMessage.getObject();
            //ftpService.storeImage(auditInfoMessage);
        } catch (Exception e) {
            log.error("Receiving exception: " + Arrays.toString(e.getStackTrace()));
        }
    }

}
