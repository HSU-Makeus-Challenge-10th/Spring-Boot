package com.example.umc10thweek4.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

public class MemberReqDTO {

    public record SignUp(
            @NotBlank(message = "이름을 입력해주세요")
            String name,

            @NotBlank(message = "닉네임을 입력해주세요")
            String nickname,

            @NotBlank(message = "이메일을 입력해주세요")
            @Email(message = "올바른 이메일 형식이 아닙니다")
            String email,

            @NotBlank(message = "비밀번호를 입력해주세요")
            String password,

            @NotBlank(message = "생년월일을 입력해주세요")
            String birthday,

            @NotBlank(message = "성별을 입력해주세요")
            String gender,

            String address,

            String phoneNum,

            List<String> foodPreferences
    ) {}

    public record GetInfo(
            Long id
    ) {}
}