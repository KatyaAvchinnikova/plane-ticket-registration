package com.example.businesslayernew.controller;

import com.example.businesslayernew.dto.user.UserAuthRequest;
import com.example.businesslayernew.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class SecurityController {

    private final SecurityService securityService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> signIn(@RequestBody UserAuthRequest auth){
        String token = securityService.authenticate(auth);
        return new ResponseEntity<>(token, HttpStatus.ACCEPTED);
    }
}
