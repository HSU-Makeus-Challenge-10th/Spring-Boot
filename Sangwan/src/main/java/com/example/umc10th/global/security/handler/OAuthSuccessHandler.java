package com.example.umc10th.global.security.handler;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.security.entity.AuthMember;
import com.example.umc10th.global.security.entity.OAuthMember;
import com.example.umc10th.global.security.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@RequiredArgsConstructor
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        BaseSuccessCode code = MemberSuccessCode.OK;

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(code.getStatus().value());

        OAuthMember member = (OAuthMember) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String accessToken = jwtUtil.createAccessToken(new AuthMember(member.getMember()));

        ApiResponse<MemberResDTO.Login> responseBody = ApiResponse.onSuccess(
                code,
                MemberConverter.toLogin(accessToken)
        );

        objectMapper.writeValue(response.getOutputStream(), responseBody);
    }
}
