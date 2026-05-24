package com.umc.study.domain.user.web.dto;

import com.umc.study.domain.user.entity.Food;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record SignUpReq(
        @NotNull(message = "서비스 이용 동의 필드는 비어있을 수 없습니다.")
        Agree agree,

        @NotBlank(message = "이름 필드는 비어있을 수 없습니다.")
        String name,

        @NotNull(message = "성별 필드는 비어있을 수 없습니다.")
        Boolean isMale,

        @NotNull(message = "생년월일 필드는 비어있을 수 없습니다.")
        LocalDate birth,

        @NotBlank(message = "주소 필드는 비어있을 수 없습니다.")
        String address,

        @NotBlank(message = "상세 주소 필드는 비어있을 수 없습니다.")
        String detailAddress,

        @NotEmpty(message = "선호 음식 배열은 비어있을 수 없습니다.")
        List<Food> foodList,

        @NotBlank(message = "이메일 필드는 비어있을 수 없습니다.") @Email(message = "이메일 형식이 맞지 않습니다.")
        String email,

        @NotBlank(message = "비밀번호 필드는 비어있을 수 없습니다.")
        String password,

        @NotBlank(message = "전화번호 필드는 비어있을 수 없습니다.")
        String phoneNum
) {

    public record Agree(
            Boolean age,
            Boolean service,
            Boolean privacy,
            Boolean location,
            Boolean marketing
            ) {}
}
