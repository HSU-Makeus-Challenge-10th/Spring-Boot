package com.example.umc10thweek4.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

public class MemberReqDTO {

    // 화원가입
    public record SignUp(
            @NotBlank String name,
            @NotBlank String nickname,
            @Email String email,
            @NotBlank String password,
            @NotBlank String birthday,
            @NotBlank String gender,
            String address,
            String phone_num,
            List<String> foodPreferences
    ) {}

    // 마이페이지
    public record GetInfo(
            Long id
    ) {}
}