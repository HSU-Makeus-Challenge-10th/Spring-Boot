package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class MemberReqDTO {

    public record SignUp (
        @NotBlank
        String name,
        @NotBlank
        String nickname,
        @Email @NotBlank
        String email,
        @NotBlank
        String password,
        @NotBlank
        String phoneNumber,
        Gender gender
    ){}

    public record Login(
        @Email @NotBlank
        String email,
        @NotBlank
        String password
    ){}

}
