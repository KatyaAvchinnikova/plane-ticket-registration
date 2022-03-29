package com.innowise.ftplayer.messaging;

import com.innowise.message.FtpInfoMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageProducer {

    private final JmsTemplate jmsTemplate;

    public void store(FtpInfoMessage message) {
        log.info("Sending...");
        jmsTemplate.convertAndSend("queue4", message);
        log.info(new StringBuilder("Images for user with email ")
                .append(message.getEmail())
                .append(" are transferred.")
                .toString());
    }

}
