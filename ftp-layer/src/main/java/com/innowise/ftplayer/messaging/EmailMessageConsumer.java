package com.innowise.ftplayer.messaging;

import com.innowise.ftplayer.domain.Photo;
import com.innowise.ftplayer.service.FtpService;
import com.innowise.message.EmailMessage;
import com.innowise.message.FtpInfoMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@Component
@Slf4j
@RequiredArgsConstructor
public class EmailMessageConsumer implements MessageListener {

    private final FtpService ftpService;

    @Override
    @JmsListener(destination = "queue3")
    public void onMessage(Message message) {
        try {
            log.info("consuming...message: " + message.toString());
            ObjectMessage objectMessage = (ObjectMessage) message;
            EmailMessage emailMessage = (EmailMessage) objectMessage.getObject();
            List<FtpInfoMessage> photos = ftpService.download(emailMessage);
        } catch (Exception e) {
            log.error("Receiving exception: " + Arrays.toString(e.getStackTrace()));
        }
    }

}
