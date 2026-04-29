package com.umc.study.global.apiPayload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.umc.study.global.apiPayload.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result", "timeStamp"})
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("result")
    private T result;

    @JsonProperty("timeStamp")
    private final String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    public static <T> ApiResponse<T> onFailure(BaseResponseCode code, T result) {
        return new ApiResponse<T>(false, code.getCode(), code.getMessage(), result);
    }

    public static <T> ApiResponse<T> onComplete(BaseResponseCode code, T result) {
        return new ApiResponse<T>(true, code.getCode(), code.getMessage(), result);
    }
}
