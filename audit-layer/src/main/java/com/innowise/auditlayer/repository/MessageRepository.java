package com.innowise.auditlayer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.innowise.message.AuditInfoMessage;

public interface MessageRepository extends MongoRepository<AuditInfoMessage, String> {
}
