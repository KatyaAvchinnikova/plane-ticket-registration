package com.example.businesslayernew.security.jwt;

import com.example.businesslayernew.domain.User;
import com.example.businesslayernew.security.JwtUserDetailsService;
import com.example.businesslayernew.service.UserService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private static final String KEY = "secret-key";

    private final JwtUserDetailsService userDetailsService;

    private final UserService userService;

    public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public String getToken(String email) {
        Date expDate = Date.from(LocalDate.now().plusDays(14).atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println(KEY);
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expDate)
                .signWith(SignatureAlgorithm.HS512, KEY)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(KEY).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException |
                UnsupportedJwtException |
                MalformedJwtException |
                SignatureException exception) {
            exception.printStackTrace();
        }

        return false;
    }

    String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public Authentication getAuthentication(String token) {
        String login = getLogin(token);

        UserDetails userDetails = userDetailsService.loadUserByUsername(login);
        User user = userService.findByEmail(login);

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    private String getLogin(String token) {
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody().getSubject();
    }

}
