package com.innowise.businesslayer.messaging;

import com.innowise.message.AuditInfoMessage;
import com.innowise.businesslayer.security.JwtUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class MessageFilter extends GenericFilterBean {

    private final MessageProducer producer;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//        if(auth != null && auth.getPrincipal() instanceof JwtUser){
//            producer.send(AuditInfoMessage
//                    .builder()
//                    .email(((JwtUser) auth.getPrincipal()).getEmail())
//                    .endpoint(request.getServletContext().getContextPath())
//                    .requestTime(LocalDateTime.now())
//                    .responseCode(((HttpServletResponse)response).getStatus())
//                    .build());
//        }

        chain.doFilter(request, response);

    }

}
