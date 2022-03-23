package com.innowise.auditlayer.repository;

import com.innowise.message.AuditInfoMessage;

public interface MongoRepository
        extends org.springframework.data.mongodb.repository.MongoRepository<AuditInfoMessage, String> {
}
