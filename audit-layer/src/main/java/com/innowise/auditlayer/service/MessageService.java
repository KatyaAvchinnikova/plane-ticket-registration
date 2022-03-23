package com.innowise.auditlayer.service;

import com.innowise.auditlayer.repository.MongoRepository;
import com.innowise.message.AuditInfoMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MongoRepository mongoRepository;

    public AuditInfoMessage save(AuditInfoMessage message){
        mongoRepository.save(message);
        return message;
    }

    public List<AuditInfoMessage> getAll(){
        return mongoRepository.findAll();
    }

}
