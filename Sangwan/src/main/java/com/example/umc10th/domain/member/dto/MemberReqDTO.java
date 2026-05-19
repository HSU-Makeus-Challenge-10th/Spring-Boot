package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

public class MemberReqDTO {

    public record MyInfoReq(
            @NotNull Long id
    ){}

    public record SignupReq(
            @NotBlank String name,
            @NotBlank @Email String email,
            @NotBlank
            @Size(min = 8, max = 16, message = "비밀번호는 8자 이상 16자 이하여야 합니다.") String password,
            @NotNull(message = "성별은 필수입니다. (MALE, FEMALE, NONE)") Gender gender,
            @NotBlank(message = "생년월일은 필수입니다.")
            @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "생년월일 형식이 올바르지 않습니다. (YYYY-MM-DD)") String birthday,
            @NotBlank String address,
            @NotEmpty(message = "약관 동의 정보는 필수입니다.")
            List<@Valid TermAgreementReq> termAgreements,
            @NotEmpty(message = "선호 음식 종류는 1개 이상 선택해야 합니다.")
            List<@NotNull @Positive Long> foodCategoryIds
    ) {}

    public record TermAgreementReq(
            @NotNull(message = "약관 ID는 필수입니다.")
            @Positive(message = "약관 ID는 양수여야 합니다.") Long termId,
            @NotNull(message = "약관 동의 여부는 필수입니다.") Boolean isAgreed
    ) {}

    public record GetInProgressMissionsReq(
            @NotNull(message = "사용자 ID는 필수입니다.") Long memberId
    ) {}
}
