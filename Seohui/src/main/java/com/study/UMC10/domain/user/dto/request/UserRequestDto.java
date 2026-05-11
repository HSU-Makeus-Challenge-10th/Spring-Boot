package com.study.UMC10.domain.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public class UserRequestDto {

    @Schema(description = "유저 정보 조회 요청")
    public record GetInfo(
            @Schema(description = "조회할 유저 ID", example = "1")
            Long id
    ) {
    }

    @Schema(description = "회원가입 요청")
    public record SignUpDto(
            ServiceAgreeDto serviceAgree,

            @Schema(description = "이름", example = "윰씨")
            String name,

            @Schema(description = "성별", example = "NONE")
            String gender,

            @Schema(description = "생년월일", example = "2001-01-01")
            String birth,

            @Schema(description = "주소", example = "서울특별시")
            String address,

            @Schema(description = "이메일", example = "umc@example.com")
            String email,

            @Schema(description = "비밀번호", example = "password1234")
            String password,

            @Schema(description = "닉네임", example = "솔")
            String nickname,

            @Schema(description = "선호하는 음식 카테고리 ID 목록", example = "[2, 5, 6]")
            List<Long> foodCategory
    ) {
    }

    @Schema(description = "서비스 이용 동의")
    public record ServiceAgreeDto(
            @Schema(description = "위치 정보 수집 동의 여부", example = "true")
            Boolean location,

            @Schema(description = "마케팅 수신 동의 여부", example = "false")
            Boolean marketing
    ) {
    }
}