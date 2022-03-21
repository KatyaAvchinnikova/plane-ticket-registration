package com.example.auditlayer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.message.AuditInfoMessage;

@Repository
public interface MessageRepository extends MongoRepository<AuditInfoMessage, String> {

}
