package com.example.umc10th.global.apiPayload;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
//제네릭 클래스 (어떤 타입의 결과값도 담을 수 있음)
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private final boolean isSuccess;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("result")
    private T result;
    //성공한 경우
    public static <T> ApiResponse<T> onSuccess(BaseSuccessCode code, T result){
        return new ApiResponse<>(true, code.getCode(), code.getMessage(), result);
    }

    //실패한 경우
    //제네릭 메서드 (호출 시 넘겨주는 데이터의 타입을 보고 알아서 T가 무엇인지 판단)
    //static 메서드에 필요한 이유 : static 메서드는 클래스 인스턴스가 생성되기 전에 메모리에 올라감
    //클래스 레벨의 <T> 사용 불가능. 따라서 메서드 자체에 <T> 선언
    public static <T> ApiResponse<T> onFailure(BaseErrorCode code, T result) {
        return new ApiResponse<>(false, code.getCode(),code.getMessage(), result);
    }
}
