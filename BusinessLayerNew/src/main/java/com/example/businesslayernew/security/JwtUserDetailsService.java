package com.example.businesslayernew.security;

import com.example.businesslayernew.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (userRepository.findByEmail(username).isEmpty()) {
            throw new UsernameNotFoundException("Email is not found");
        }
        return new CustomUserDetails(userRepository.findByEmail(username).get());
    }

}
