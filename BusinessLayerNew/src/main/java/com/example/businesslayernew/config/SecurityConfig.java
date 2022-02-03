package com.example.businesslayernew.config;

import com.example.businesslayernew.domain.Role;
import com.example.businesslayernew.security.jwt.JwtConfigurer;
import com.example.businesslayernew.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtProvider jwtProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .mvcMatchers(HttpMethod.POST, "/api/airports").hasAuthority(Role.ADMIN.getRole())
                .mvcMatchers(HttpMethod.PATCH, "/api/airports/*").hasAuthority(Role.ADMIN.getRole())
                .mvcMatchers(HttpMethod.DELETE, "/api/airports/*").hasAuthority(Role.ADMIN.getRole())

                .mvcMatchers(HttpMethod.POST, "/api/flights").hasAuthority(Role.ADMIN.getRole())
                .mvcMatchers(HttpMethod.PATCH, "/api/flights/*").hasAuthority(Role.ADMIN.getRole())
                .mvcMatchers(HttpMethod.DELETE, "/api/flights/*").hasAuthority(Role.ADMIN.getRole())

                .mvcMatchers(HttpMethod.POST, "/api/tickets").hasAnyAuthority(Role.ADMIN.getRole(), Role.USER.getRole())
                .mvcMatchers(HttpMethod.PATCH, "/api/tickets/*").hasAnyAuthority(Role.ADMIN.getRole(), Role.USER.getRole())
                .mvcMatchers(HttpMethod.DELETE, "/api/tickets/*").hasAnyAuthority(Role.ADMIN.getRole(), Role.USER.getRole())
                .mvcMatchers(HttpMethod.GET, "/api/tickets").hasAnyAuthority(Role.ADMIN.getRole(), Role.USER.getRole())
                .mvcMatchers(HttpMethod.GET, "/api/tickets/*").hasAnyAuthority(Role.ADMIN.getRole(), Role.USER.getRole())

                .anyRequest()
                .permitAll()

                .and()
                .apply(new JwtConfigurer(jwtProvider));
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder(10);
    }
}
