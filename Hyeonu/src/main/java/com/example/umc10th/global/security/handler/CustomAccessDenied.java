package com.example.umc10th.global.security.handler;

import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.code.GeneralErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

// 권한이 없는 사용자가 접근할 떄 JSON으로 응답해주는 핸들러
// AccessDeniedHandler는 로그인은 했지만 권한이 없을 때 어떻게 처리할지
// 401 Unauthorized 로그인 자체를 안함/ 403 Forbidden 로그인은 했지만 권한 없음 (이 핸들러가 처리)
public class CustomAccessDenied implements AccessDeniedHandler {

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    )throws IOException {
        // Java 객체->JSON문자열로 변환해주는 도구
        ObjectMapper objectMapper=new ObjectMapper();
        BaseErrorCode code= GeneralErrorCode.FORBIDDEN; // 403 Forbidden 에러코드 가져옴

        // 응답 Content-Type, HTTP 상태코드 정의
        response.setContentType("application/json;charset=UTF-8"); // JSON 형식으로 응답
        response.setStatus(code.getStatus().value()); // HTTP 상태코드 403 설정

        // Response Body에 응답통일한 객체를 넣기
        ApiResponse<Void> errorResponse=ApiResponse.onFailure(code,null);

        // 실제 Response로 덮어쓰기 (만든 객체를 실제 HTTP 응답 바디에 JSON으로 작성)
        objectMapper.writeValue(response.getOutputStream(),errorResponse);
    }
}
