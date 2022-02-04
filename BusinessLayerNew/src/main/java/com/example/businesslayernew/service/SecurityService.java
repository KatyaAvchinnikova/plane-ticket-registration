package com.example.businesslayernew.service;

import com.example.businesslayernew.domain.User;
import com.example.businesslayernew.dto.user.UserAuthRequest;
import com.example.businesslayernew.exception.UserBadCredentialsException;
import com.example.businesslayernew.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final JwtProvider jwtProvider;

    public String authenticate(UserAuthRequest auth) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getUserName(),
                    auth.getPassword()));

            User user = userService.findByUserName(auth.getUserName());

            if (user == null) {
                throw new UserBadCredentialsException("Incorrect user password or login");
            }

            return jwtProvider.createToken(auth.getUserName(), user.getRole().getRole());

        } catch (BadCredentialsException e) {
            throw new UserBadCredentialsException("Incorrect user password or login");
        }
    }

}
