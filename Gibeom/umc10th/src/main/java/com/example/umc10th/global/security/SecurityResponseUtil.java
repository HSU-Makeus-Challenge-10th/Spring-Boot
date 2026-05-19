package com.example.umc10th.global.security;

import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SecurityResponseUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void writeErrorResponse(HttpServletResponse response, BaseErrorCode code) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(code.getStatus().value());
        objectMapper.writeValue(response.getOutputStream(), ApiResponse.onFailure(code, null));
    }
}
