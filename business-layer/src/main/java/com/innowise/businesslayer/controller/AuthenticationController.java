package com.innowise.businesslayer.controller;

import com.innowise.businesslayer.dto.user.UserAuthRequest;
import com.innowise.businesslayer.security.JwtDto;
import com.innowise.businesslayer.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/login")
    public ResponseEntity<JwtDto> signIn(@RequestBody UserAuthRequest auth) {
        JwtDto token = securityService.authenticate(auth);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtDto> updateRefresh(@RequestBody String refreshToken) {
        JwtDto jwtDtoTokens = securityService.updateRefreshToken(refreshToken);
        return new ResponseEntity<>(jwtDtoTokens, HttpStatus.OK);
    }

}
