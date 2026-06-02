package com.study.UMC10.global.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.UMC10.domain.user.dto.response.UserResponseDto;
import com.study.UMC10.global.security.CustomOAuth2User;
import com.study.UMC10.global.security.CustomUserDetails;
import com.study.UMC10.global.security.util.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {

        CustomOAuth2User oauth2User = (CustomOAuth2User) authentication.getPrincipal();

        CustomUserDetails userDetails = new CustomUserDetails(oauth2User.getUser());

        String token = jwtUtil.createAccessToken(userDetails);

        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> result = new HashMap<>();
        result.put("isSuccess", true);
        result.put("message", "카카오 로그인 성공 및 토큰 발급 완료");

        UserResponseDto.LoginResultDto tokenDto = UserResponseDto.LoginResultDto.builder()
                .accessToken(token)
                .build();
        result.put("result", tokenDto);

        mapper.writeValue(response.getOutputStream(), result);
    }
}