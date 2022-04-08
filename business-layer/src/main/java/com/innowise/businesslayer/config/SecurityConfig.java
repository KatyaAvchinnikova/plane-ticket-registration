package com.innowise.businesslayer.config;

import com.innowise.businesslayer.security.jwt.JwtConfigurer;
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
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtConfigurer jwtConfigurer;
    private final AuthenticationEntryPoint appAuthenticationEntryPoint;
    private final AccessDeniedHandler appAccessDeniedHandler;

    private static final String USER_ENDPOINTS = "/api/user/**";
    private static final String AIRPORT_ENDPOINTS = "/api/user/airports/**";
    private static final String FLIGHT_ENDPOINTS = "/api/user/flights/**";
    private static final String TICKET_ENDPOINTS = "/api/user/tickets/**";

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
            .antMatchers(USER_ENDPOINTS).authenticated()
            .antMatchers(HttpMethod.DELETE, AIRPORT_ENDPOINTS).authenticated()
            .antMatchers(HttpMethod.POST, AIRPORT_ENDPOINTS).authenticated()
            .antMatchers(HttpMethod.PATCH, AIRPORT_ENDPOINTS).authenticated()
            .antMatchers(HttpMethod.DELETE, FLIGHT_ENDPOINTS).authenticated()
            .antMatchers(HttpMethod.POST, FLIGHT_ENDPOINTS).authenticated()
            .antMatchers(HttpMethod.PATCH, FLIGHT_ENDPOINTS).authenticated()
            .antMatchers(TICKET_ENDPOINTS).authenticated()
            .anyRequest()
            .permitAll()
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(appAuthenticationEntryPoint)
            .accessDeniedHandler(appAccessDeniedHandler)
            .and()
            .apply(jwtConfigurer);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(10);
    }

}
