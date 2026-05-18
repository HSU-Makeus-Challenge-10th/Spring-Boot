package com.example.umc10thweek4.domain.mission.converter;

import com.example.umc10thweek4.domain.member.entity.Member;
import com.example.umc10thweek4.domain.mission.dto.MissionReqDTO;
import com.example.umc10thweek4.domain.mission.dto.MissionResDTO;
import com.example.umc10thweek4.domain.mission.entity.Mission;
import com.example.umc10thweek4.domain.mission.entity.mapping.UserMission;
import com.example.umc10thweek4.domain.store.entity.Store;

import java.util.List;

public class MissionConverter {

    public static Mission toMission(
            Store store,
            MissionReqDTO.CreateMission dto
    ) {
        return Mission.builder()
                .store(store)
                .missionTitle(dto.missionTitle())
                .missionDetail(dto.missionDetail())
                .missionDeadline(dto.missionDeadline())
                .missionReward(dto.missionReward())
                .targetAmount(dto.targetAmount())
                .build();
    }

    public static MissionResDTO.GetMission toGetStoreMission(Mission mission) {
        return new MissionResDTO.GetMission(
                mission.getId(),
                mission.getMissionTitle(),
                mission.getMissionDetail(),
                mission.getMissionReward(),
                mission.getTargetAmount(),
                mission.getMissionDeadline()
        );
    }

    // 홈 화면 추천 미션
    public static MissionResDTO.Home.RecommendedMission toRecommendedMission(Mission mission) {
        return new MissionResDTO.Home.RecommendedMission(
                mission.getId(),
                mission.getStore().getStoreName(),
                mission.getStore().getCategory(),
                Math.toIntExact(mission.getMissionReward()),
                mission.getMissionDeadline().toString(),
                mission.getTargetAmount()
        );
    }

    // 홈 화면 전체 미션
    public static MissionResDTO.Home toHome(
            Member member,
            List<Mission> recommendedMissions
    ) {
        List<MissionResDTO.Home.RecommendedMission> recommendedList = recommendedMissions.stream()
                .map(MissionConverter::toRecommendedMission)
                .toList();

        return new MissionResDTO.Home(
                "안암동",                            // TODO: 실제 지역 연동
                Math.toIntExact(member.getTotalPoints()),
                0,                                   // TODO: 진행중 미션 수
                member.getCompleteNum(),
                5000,                                // TODO: 목표 보상
                recommendedList,
                false                                // TODO: NoticeService 연동
        );
    }

    // 내 미션
    public static MissionResDTO.UserMissionList.UserMission toUserMission(UserMission um) {
        return new MissionResDTO.UserMissionList.UserMission(
                um.getId(),
                um.getMission().getId(),
                um.getMission().getMissionTitle(),
                um.getMission().getStore().getStoreName(),
                0,                                   // TODO: recognizeAmount
                um.getStatus(),
                Math.toIntExact(um.getMission().getMissionReward())
        );
    }

    // 내 미션 목록
    public static MissionResDTO.UserMissionList toUserMissionList(List<UserMission> userMissions) {
        return new MissionResDTO.UserMissionList(
                userMissions.stream()
                        .map(MissionConverter::toUserMission)
                        .toList()
        );
    }

    // 미션 참여
    public static MissionResDTO.Participate toParticipate(UserMission userMission) {
        return new MissionResDTO.Participate(
                userMission.getId(),
                userMission.getMission().getId(),
                userMission.getStatus()
        );
    }

    // 미션 완료
    public static MissionResDTO.Complete toComplete(UserMission userMission) {
        return new MissionResDTO.Complete(
                userMission.getStatus(),
                userMission.getMission().getMissionTitle(),
                userMission.getMission().getMissionDetail(),
                Math.toIntExact(userMission.getMission().getMissionReward()),
                userMission.getUpdatedAt()
        );
    }

    public static <T> MissionResDTO.Pagination<T> toPagination(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ) {
        return MissionResDTO.Pagination.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }
}
