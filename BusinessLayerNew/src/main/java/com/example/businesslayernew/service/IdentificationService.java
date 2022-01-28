package com.example.businesslayernew.service;

import com.example.businesslayernew.repository.UserRepository;
import com.example.businesslayernew.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class IdentificationService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (userRepository.findByEmail(username).isEmpty()) {
            throw new UsernameNotFoundException("Email is not found");
        }
        return new SecurityUser(userRepository.findByEmail(username).get());
    }

}
