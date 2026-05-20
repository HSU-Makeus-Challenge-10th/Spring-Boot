package com.example.umc10th.domain.member.dto;

import lombok.Builder;

import java.util.List;

public class MemberResDTO {

    //마이페이지
    @Builder
    public record GetInfo(
            String nickname,
            String email,
            String phoneNumber,
            Integer userPoint
    ){}

    //홈 화면
    @Builder
    public record HomeResultDto(
            Integer point,
            String region,
            List<HomeMissionDto> missions,
            boolean hasNext
    ){}

    //홈 화면 내 미션 목록
    @Builder
    public record HomeMissionDto(
            Long missionId,
            String storeName,
            String storeCategory,
            Integer rewardPoint,
            Integer deadline,
            String status
    ){}
}
