package com.example.businesslayernew.service;

import com.example.businesslayernew.domain.User;
import com.example.businesslayernew.dto.user.UserAuthRequest;
import com.example.businesslayernew.exception.AppException;
import com.example.businesslayernew.repository.UserRepository;
import com.example.businesslayernew.security.JwtDto;
import com.example.businesslayernew.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public JwtDto authenticate(UserAuthRequest auth) {
        AppException ex = new AppException("Incorrect user password or login",
                HttpStatus.UNAUTHORIZED);
        User user = userService.findByUserName(auth.getUserName(), ex);
        boolean validPassword = passwordEncoder.matches(auth.getPassword(), user.getPassword());
        if (!validPassword) {
            throw ex;
        }

        String accessToken = jwtProvider.createAccessToken(auth.getUserName(), user.getRole().name());
        String refreshToken = jwtProvider.createRefreshToken(auth.getUserName());
        String tokenId = jwtProvider.getTokenId(refreshToken);
        user.setRefreshId(tokenId);
        return new JwtDto(accessToken, refreshToken);
    }

    //TODO: Везде отдаем энтити, а здесь - дто?
    // Да, здесь дто
    @Transactional
    public JwtDto updateRefreshToken(String refreshToken) {
        String tokenId = jwtProvider.getTokenId(refreshToken);
        User user = userRepository.findUserByRefreshId(tokenId)
                                  .orElseThrow(() -> new RuntimeException("Your token is invalid"));
        String accessToken = jwtProvider.createAccessToken(user.getUserName(), user.getRole().name());
        String refreshTokenNew = jwtProvider.createRefreshToken(user.getUserName());
        String tokenIdNew = jwtProvider.getTokenId(refreshTokenNew);
        user.setRefreshId(tokenIdNew);
        return new JwtDto(accessToken, refreshTokenNew);
    }

}
