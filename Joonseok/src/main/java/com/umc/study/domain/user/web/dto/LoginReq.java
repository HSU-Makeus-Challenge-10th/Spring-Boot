package com.umc.study.domain.user.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginReq {

    @Email(message = "이메일 형식이 아닙니다.")
    private final String loginId;

    @NotBlank(message = "비밀번호 필드는 비어있을 수 없습니다.")
    private final String password;
}
