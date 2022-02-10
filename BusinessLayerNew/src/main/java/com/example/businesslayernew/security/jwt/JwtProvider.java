package com.example.businesslayernew.security.jwt;

import com.example.businesslayernew.security.JwtUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

@Component
public class JwtProvider {
    private static final String KEY = "secret-key";
    private static final long ACCESS_TOKEN_LIFETIME = 30 * 24 * 60 * 60 * 1000L;
    private static final long REFRESH_TOKEN_LIFETIME = 30 * 24 * 60 * 60 * 1000L;
    private final JwtUserDetailsService userDetailsService;
    private String secret;

    @Autowired
    public JwtProvider(@Lazy JwtUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret)
                   .parseClaimsJws(token)
                   .getBody()
                   .getSubject();
    }

    public String createAccessToken(String username, String role) {
        Map<String, Object> header = new HashMap<>();
        header.put("alg", "HS256");
        header.put("typ", "JWT");

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("role", role);

        Date now = new Date();
        Date validity = new Date(now.getTime() + ACCESS_TOKEN_LIFETIME);

        return Jwts.builder()
                   .setHeader(header)
                   .setClaims(claims)
                   .setIssuedAt(now)
                   .setExpiration(validity)
                   .signWith(SignatureAlgorithm.HS256, secret)
                   .compact();
    }

    public String createRefreshToken(String username) {
        Map<String, Object> header = new HashMap<>();
        header.put("alg", "HS512");
        header.put("typ", "JWT");

        Claims claims = Jwts.claims().setId(UUID.randomUUID().toString())
                            .setSubject(username);

        Date now = new Date();
        Date validity = new Date(now.getTime() + REFRESH_TOKEN_LIFETIME);

        return Jwts.builder()
                   .setHeader(header)
                   .setClaims(claims)
                   .setIssuedAt(now)
                   .setExpiration(validity)
                   .signWith(SignatureAlgorithm.HS512, secret)
                   .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new RuntimeException("JWT token is expired or invalid");
            //throw new NotValidTokenException("JWT token is expired or invalid");
        }
    }

    String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    public Authentication getAuthentication(String token) {
        String login = getLogin(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(login);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    private String getLogin(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public String getTokenId(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        return claimsJws.getBody().getId();
    }

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(KEY.getBytes());
    }
}
