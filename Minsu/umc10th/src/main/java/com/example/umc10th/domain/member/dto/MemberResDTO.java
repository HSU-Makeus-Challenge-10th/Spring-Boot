package com.example.umc10th.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

public class MemberResDTO {

    @Builder
    public record GetInfo(
            @Schema(description = "이름", example = "홍길동")
            String name,
            @Schema(description = "프로필 이미지 URL", example = "https://example.com/profile.jpg")
            String profileUrl,
            @Schema(description = "이메일", example = "user@example.com")
            String email,
            @Schema(description = "전화번호", example = "010-1234-5678")
            String phoneNumber,
            @Schema(description = "보유 포인트", example = "2500")
            Integer point
    ) {}

    @Builder
    public record PointInfo(
            @JsonProperty("user_id")
            @Schema(description = "유저 ID", example = "1")
            String userId,
            @Schema(description = "닉네임", example = "nickname012")
            String nickname,
            @Schema(description = "보유 포인트", example = "2500")
            Integer point
    ) {}

    @Builder
    public record SignUpInfo(
            @JsonProperty("user_id")
            @Schema(description = "유저 ID", example = "1")
            String userId,
            @Schema(description = "이름", example = "홍길동")
            String name,
            @Schema(description = "이메일", example = "user@example.com")
            String email,
            @JsonProperty("access_token")
            @Schema(description = "액세스 토큰", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
            String accessToken,
            @JsonProperty("refresh_token")
            @Schema(description = "리프레시 토큰", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
            String refreshToken
    ) {}
}
