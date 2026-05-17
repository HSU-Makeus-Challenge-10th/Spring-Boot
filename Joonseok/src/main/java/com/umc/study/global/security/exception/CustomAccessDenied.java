package com.umc.study.global.security.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umc.study.global.apiPayload.ApiResponse;
import com.umc.study.global.apiPayload.code.BaseResponseCode;
import com.umc.study.global.apiPayload.code.GeneralErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAccessDenied implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException, ServletException {

        BaseResponseCode errorCode = GeneralErrorCode.FORBIDDEN;

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(errorCode.getStatus().value());

        ApiResponse<Void> errorResponse = ApiResponse.onFailure(errorCode, null);

        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
