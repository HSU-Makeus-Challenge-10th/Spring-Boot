package com.umc.study.global.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umc.study.global.apiPayload.ApiResponse;
import com.umc.study.global.apiPayload.code.GeneralErrorCode;
import com.umc.study.global.security.service.CustomUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

            // token 추출
            String accessToken = extractToken(request);

            // token이 없으면 다음 필터 체인 진행
            if(accessToken == null || accessToken.isBlank())
                filterChain.doFilter(request,response);

        try {

            // 토큰이 만료되었으면 다음 필터로 넘기지 않고 예외
            jwtTokenProvider.isExpired(accessToken);

        } catch(JwtException e) {
            set401Response(response);
        }

        Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request,response);

    }

    private void set401Response(@org.jspecify.annotations.NonNull HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        ApiResponse<?> errorResponse = ApiResponse.onFailure(GeneralErrorCode.UNAUTHORIZED, null);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }

    private String extractToken(HttpServletRequest request) {

        // HttpServletRequest에서 Authorization 필드 추출
        String bearerToken = request.getHeader("Authorization");

        if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        } else {
            return null;
        }
    }
}
