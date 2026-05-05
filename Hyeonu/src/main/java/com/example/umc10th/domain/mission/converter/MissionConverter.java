package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;

public class MissionConverter {
    public static MemberMission toMemberMission(Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGING)
                .build();
    }

    public static MissionResDTO.MissionChallengeResult toMissionChallengeResult(MemberMission memberMission) {
        return MissionResDTO.MissionChallengeResult.builder()
                .memberMissionId(memberMission.getId())
                .createdAt(memberMission.getCreatedAt())
                .build();
    }

    // 내가 진행 중인 미션 변환 (MemberMission -> DTO)
    public static MissionResDTO.MissionSummaryDTO toMissionSummaryDTO(MemberMission mm) {
        return MissionResDTO.MissionSummaryDTO.builder()
                .missionId(mm.getMission().getId())
                .storeName(mm.getMission().getStore().getName())
                .rewardPoint(mm.getMission().getRewardPoint())
                .content(mm.getMission().getContent())
                .status(mm.getStatus().name())
                .build();
    }
    // 지역별 도전 가능 미션 변환 (Mission -> DTO)
    public static MissionResDTO.MissionSummaryDTO toMissionSummaryDTO(Mission mission) {
        return MissionResDTO.MissionSummaryDTO.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .rewardPoint(mission.getRewardPoint())
                .content(mission.getContent())
                .status("CHALLENGE_AVAILABLE") // 도전 가능 상태를 나타내는 임의의 문자열
                .build();
    }
}