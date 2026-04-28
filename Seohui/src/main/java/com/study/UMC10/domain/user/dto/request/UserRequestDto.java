package com.study.UMC10.domain.user.dto.request;

import java.util.List;

public class UserRequestDto {

    public record GetInfo(
            Long id
    ) {
    }

    // 회원가입
    public record SignUpDto(
            ServiceAgreeDto serviceAgree,
            String name,
            String gender,
            String birth,
            String address,
            String email,
            String password,
            String nickname,
            List<Long> foodCategory
    ) {
    }

    // 회원가입 서비스 동의 여부
    public record ServiceAgreeDto(
            Boolean location,
            Boolean marketing
    ) {
    }
}