package com.example.umc10thweek4.global.security.handler;

import com.example.umc10thweek4.global.apiPayload.ApiResponse;
import com.example.umc10thweek4.global.apiPayload.code.BaseErrorCode;
import com.example.umc10thweek4.global.apiPayload.code.BaseSuccessCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class SecurityResponseWriter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void writeFailure(HttpServletResponse response, BaseErrorCode errorCode) throws IOException {
        response.setStatus(errorCode.getStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        objectMapper.writeValue(response.getWriter(), ApiResponse.onFailure(errorCode, null));
    }

    public void writeSuccess(HttpServletResponse response, BaseSuccessCode successCode) throws IOException {
        response.setStatus(successCode.getStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        objectMapper.writeValue(response.getWriter(), ApiResponse.onSuccess(successCode, null));
    }
}
