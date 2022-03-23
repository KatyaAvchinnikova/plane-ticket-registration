package com.innowise.auditlayer.service;

import com.innowise.auditlayer.repository.MessageRepository;
import com.innowise.message.AuditInfoMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public AuditInfoMessage save(AuditInfoMessage message){
        messageRepository.save(message);
        return message;
    }

    public List<AuditInfoMessage> getAll(){
        return messageRepository.findAll();
    }

}
