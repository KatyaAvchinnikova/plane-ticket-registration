package com.example.businesslayernew.security;

import com.example.businesslayernew.domain.User;
import com.example.businesslayernew.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public JwtUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUserName(username);
        return JwtUserFactory.create(user);
    }
}
