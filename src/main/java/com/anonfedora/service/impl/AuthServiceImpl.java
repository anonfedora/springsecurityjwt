package com.anonfedora.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.anonfedora.config.JwtTokenProvider;
import com.anonfedora.dto.LoginDto;
import com.anonfedora.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public String login(LoginDto loginDto) {
        // 01
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

        // 02
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 03
        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

}
