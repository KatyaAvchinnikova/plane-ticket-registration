package com.example.businesslayernew.controller;

import com.example.businesslayernew.dto.user.UserAuthRequest;
import com.example.businesslayernew.security.JwtDto;
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
@RequestMapping("/api/authentication")
@RequiredArgsConstructor
public class AuthenticationController {
    private final SecurityService securityService;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtDto> signIn(@RequestBody UserAuthRequest auth){
        JwtDto token = securityService.authenticate(auth);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping(value = "/refresh", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtDto> updateRefresh(@RequestBody String refreshToken){
        JwtDto jwtDtoTokens = securityService.updateRefreshToken(refreshToken);
        return new ResponseEntity<>(jwtDtoTokens, HttpStatus.OK);
    }
}
