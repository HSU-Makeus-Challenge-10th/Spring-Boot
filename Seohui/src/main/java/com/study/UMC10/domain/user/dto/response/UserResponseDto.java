package com.study.UMC10.domain.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

public class UserResponseDto {

    @Builder
    @Schema(description = "유저 마이페이지 정보")
    public record GetInfo(
            @Schema(description = "이름", example = "윰씨")
            String name,

            @Schema(description = "닉네임", example = "솔")
            String nickname,

            @Schema(description = "이메일", example = "umc@example.com")
            String email,

            @Schema(description = "전화번호 인증 여부", example = "true")
            Boolean phoneVerified,

            @Schema(description = "전화번호", example = "010-1234-5678")
            String phoneNum,

            @Schema(description = "현재 보유한 총 포인트", example = "1500")
            Integer totalPoint
    ) {
    }

    @Builder
    @Schema(description = "회원가입 성공")
    public record SignUpResultDto(
            @Schema(description = "생성된 유저 ID", example = "3")
            Long userId,

            @Schema(description = "생성된 유저 이름", example = "윰씨")
            String name
    ) {
    }

    @Builder
    @Schema(description = "홈 화면")
    public record HomeResultDto(
            @Schema(description = "현재 설정된 지역", example = "안암동")
            String local,

            @Schema(description = "유저의 현재 포인트", example = "2500")
            Integer point,

            @Schema(description = "성공한 미션 개수", example = "5")
            Integer missionSuccess,

            @Schema(description = "해당 지역의 미션 목록")
            List<HomeMissionDto> missions
    ) {
    }

    @Builder
    @Schema(description = "홈 화면 내 미션 목록")
    public record HomeMissionDto(
            @Schema(description = "미션 ID", example = "10")
            Long missionId,

            @Schema(description = "가게 이름", example = "반이마라 안암점")
            String storeName,

            @Schema(description = "가게 카테고리", example = "중식")
            String storeCategory,

            @Schema(description = "보상 리워드", example = "500")
            Integer reward,

            @Schema(description = "마감기한(일)", example = "15")
            Integer deadline,

            @Schema(description = "미션 상태", example = "도전 가능")
            String status
    ) {
    }
}