package com.umc.study.global.apiPayload.handler;

import com.umc.study.global.apiPayload.ApiResponse;
import com.umc.study.global.apiPayload.code.BaseResponseCode;
import com.umc.study.global.apiPayload.code.GeneralErrorCode;
import com.umc.study.global.apiPayload.exception.BaseException;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ApiResponse<?> errorResponse = ApiResponse.onFailure(
                GeneralErrorCode.INVALID_HTTP_MESSAGE_BODY,
                e.getFieldError().getDefaultMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponse<Void>> handleBaseException(BaseException e) {
        BaseResponseCode errorCode = e.getErrorCode();
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ApiResponse.onFailure(errorCode, null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception e) {
        BaseResponseCode errorCode = GeneralErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(errorCode.getStatus())
                .body(ApiResponse.onFailure(errorCode, e.getMessage()));
    }
}
