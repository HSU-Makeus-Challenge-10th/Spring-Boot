package com.umc.study.domain.user.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class AuthPhoneNumReq {

    @NotBlank(message = "전화번호 필드는 비어있을 수 없습니다.") // phone num 정규식 검증
    private final String phoneNum;
}
