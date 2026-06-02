package com.example.umc10thweek4.global.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AuthReqDTO {

    public record Login(
            @NotBlank(message = "이메일을 입력해주세요")
            @Email(message = "올바른 이메일 형식이 아닙니다")
            String email,

            @NotBlank(message = "비밀번호를 입력해주세요")
            String password
    ) {}
}
