package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class MemberReqDTO {

    public record MyInfoReq(
            @NotNull Long id
    ){}

    public record SignupReq(
            @NotBlank String name,
            @NotNull(message = "성별은 필수입니다. (MALE, FEMALE, NONE)") Gender gender,
            @NotBlank(message = "생년월일은 필수입니다.")
            @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "생년월일 형식이 올바르지 않습니다. (YYYY-MM-DD)") String birthday,
            @NotBlank String address
    ) {}

    public record GetInProgressMissionsReq(
            @NotNull(message = "사용자 ID는 필수입니다.") Long memberId
    ) {}
}
