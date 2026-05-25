package com.umc.study.global.security;

import com.umc.study.global.apiPayload.ApiResponse;
import com.umc.study.global.apiPayload.code.BaseResponseCode;
import com.umc.study.global.apiPayload.code.GeneralErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomEntryPoint implements AuthenticationEntryPoint {

    private final JsonMapper jsonMApper;

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException, ServletException {

        BaseResponseCode errorCode = GeneralErrorCode.UNAUTHORIZED;

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(errorCode.getStatus().value());

        ApiResponse<Void> errorResponse = ApiResponse.onFailure(errorCode, null);

        response.getWriter().write(jsonMApper.writeValueAsString(errorResponse));
    }
}
