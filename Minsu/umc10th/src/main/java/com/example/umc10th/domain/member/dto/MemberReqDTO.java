package com.example.umc10th.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class MemberReqDTO {

    public record GetInfo(
            @Schema(description = "유저 ID", example = "1")
            Long id
    ) {}

    public record SignUp(
            @Schema(description = "이름", example = "홍길동")
            String name,
            @Schema(description = "성별 (MALE/FEMALE)", example = "MALE")
            String gender,
            @Schema(description = "생년월일 (yyyy-MM-dd)", example = "1999-03-15")
            String birthDate,
            @Schema(description = "상세주소", example = "서울시 성북구 안암동")
            String detailAddress,
            @Schema(description = "이메일", example = "user@example.com")
            String email,
            @Schema(description = "전화번호", example = "010-1234-5678")
            String phoneNumber,
            @Schema(description = "선호 음식 ID 목록", example = "[1, 3]")
            List<Long> foodTypeIds,
            @Schema(description = "동의 약관 ID 목록", example = "[1, 2]")
            List<Long> termsIds,
            @Schema(description = "소셜 타입 (KAKAO/GOOGLE/APPLE)", example = "KAKAO")
            String socialType,
            @Schema(description = "소셜 토큰", example = "kakao_oauth_token_string")
            String socialToken
    ) {}
}
