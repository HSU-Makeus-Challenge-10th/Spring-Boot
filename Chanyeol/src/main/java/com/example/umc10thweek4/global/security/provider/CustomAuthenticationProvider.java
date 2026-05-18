package com.example.umc10thweek4.global.security.provider;

import com.example.umc10thweek4.global.security.service.CustomUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    public CustomAuthenticationProvider(
            CustomUserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder
    ) {
        super(userDetailsService);
        setPasswordEncoder(passwordEncoder);
    }

    @Override
    protected void additionalAuthenticationChecks(
            UserDetails userDetails,
            UsernamePasswordAuthenticationToken authentication
    ) throws AuthenticationException {
        super.additionalAuthenticationChecks(userDetails, authentication);

        // TODO: 여기에 추가 검증 로직 작성
        // 예: 탈퇴 회원, 정지 회원, 이메일 인증 여부 등
    }
}
