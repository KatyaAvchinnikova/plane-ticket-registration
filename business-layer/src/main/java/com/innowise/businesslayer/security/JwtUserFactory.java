package com.innowise.businesslayer.security;

import com.innowise.businesslayer.domain.Role;
import com.innowise.businesslayer.domain.User;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@NoArgsConstructor
public class JwtUserFactory {
    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUserName(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getRole())
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(Role userRole) {
        return List.of(new SimpleGrantedAuthority(userRole.name()));
    }
}
