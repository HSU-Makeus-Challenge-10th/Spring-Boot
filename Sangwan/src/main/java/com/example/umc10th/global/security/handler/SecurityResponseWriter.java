package com.example.umc10th.global.security.handler;

import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class SecurityResponseWriter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void writeSuccess(HttpServletResponse response, BaseSuccessCode code) throws IOException {
        writeSuccess(response, code, null);
    }

    public <T> void writeSuccess(HttpServletResponse response, BaseSuccessCode code, T result) throws IOException {
        write(response, code.getStatus().value(), ApiResponse.onSuccess(code, result));
    }

    public void writeFailure(HttpServletResponse response, BaseErrorCode code) throws IOException {
        writeFailure(response, code, null);
    }

    public <T> void writeFailure(HttpServletResponse response, BaseErrorCode code, T result) throws IOException {
        write(response, code.getStatus().value(), ApiResponse.onFailure(code, result));
    }

    private void write(HttpServletResponse response, int status, ApiResponse<?> body) throws IOException {
        response.setStatus(status);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("application/json;charset=UTF-8");
        objectMapper.writeValue(response.getOutputStream(), body);
    }
}
