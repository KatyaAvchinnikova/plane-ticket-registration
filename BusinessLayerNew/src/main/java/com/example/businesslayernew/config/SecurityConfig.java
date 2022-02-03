package com.example.businesslayernew.config;

import com.example.businesslayernew.domain.Role;
import com.example.businesslayernew.security.jwt.JwtFilter;
import com.example.businesslayernew.service.AuthenticationProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationProviderService authenticationProvider;

    //private final JwtFilter jwtFilter;

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
                .permitAll();

//                .and()
//                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder(10);
    }
}
