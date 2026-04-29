package com.study.UMC10.domain.user.dto.response;

import lombok.Builder;

import java.util.List;

public class UserResponseDto {

    // 마이페이지
    @Builder
    public record GetInfo(
            String name,
            String nickname,
            String email,
            Boolean phoneVerified,
            String phoneNum,
            Integer totalPoint
    ) {
    }

    // 회원가입 성공
    @Builder
    public record SignUpResultDto(
            Long userId,
            String name
    ) {
    }

    // 홈 화면
    @Builder
    public record HomeResultDto(
            String local,
            Integer point,
            Integer missionSuccess,
            List<HomeMissionDto> missions
    ) {
    }

    // 홈 화면 내 미션 목록
    @Builder
    public record HomeMissionDto(
            Long missionId,
            String storeName,
            String storeCategory,
            Integer reward,
            Integer deadline,
            String status
    ) {
    }
}