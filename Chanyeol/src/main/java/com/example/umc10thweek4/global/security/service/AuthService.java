package com.example.umc10thweek4.global.security.service;

import com.example.umc10thweek4.global.security.dto.AuthReqDTO;
import com.example.umc10thweek4.global.security.dto.AuthResDTO;
import com.example.umc10thweek4.global.security.entity.AuthMember;
import com.example.umc10thweek4.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthResDTO.Login login(AuthReqDTO.Login request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        AuthMember authMember = (AuthMember) authentication.getPrincipal();
        String accessToken = jwtUtil.createAccessToken(authMember);

        return AuthResDTO.Login.of(accessToken);
    }
}
