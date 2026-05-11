package com.example.umc10th.domain.mission.dto;

public class MissionReqDTO {
    // 미션 도전하기 요청
    public record ChallengeDTO(
            Long memberId
    ){}
}
