package com.example.umc10th.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class MemberReqDTO {

    public record MyInfoReq(
            @NotBlank Long id
    ){}

    public record SignupReq(
            @NotBlank String name,
            @NotBlank String gender,
            @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "생년월일 형식이 올바르지 않습니다. (YYYY-MM-DD)") String birthday,
            @NotBlank String address
    ) {}
}
