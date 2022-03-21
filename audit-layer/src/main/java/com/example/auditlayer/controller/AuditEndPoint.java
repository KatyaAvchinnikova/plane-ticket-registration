package com.example.auditlayer.controller;

import com.example.auditlayer.service.MessageService;
import com.example.message.AuditInfoMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/flights")
public class AuditEndPoint {
    private final MessageService messageService;

    private static final String TOKEN = "Token";
    private static final String REQUEST_BODY = "";
    private static final String AUTHORIZATION = "Authorization";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String BUSINESS_URL = "http://localhost:7770";

    @GetMapping
    public List<AuditInfoMessage> getAllAudit(){
        return messageService.getAll();
    }
}
