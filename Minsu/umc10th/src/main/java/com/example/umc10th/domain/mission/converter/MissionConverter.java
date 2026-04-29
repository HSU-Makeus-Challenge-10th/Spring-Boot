package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.ActivatedMission;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.enums.MissionState;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class MissionConverter {

    public static MissionResDTO.ActivatedMissionInfo toActivatedMissionInfo(ActivatedMission am) {
        String stateStr = am.getState() == MissionState.ONGOING ? "진행중" : "진행완료";
        return MissionResDTO.ActivatedMissionInfo.builder()
                .activatedMissionId(String.valueOf(am.getId()))
                .missionId(String.valueOf(am.getMission().getId()))
                .state(stateStr)
                .startedAt(am.getStartedAt())
                .build();
    }

    public static MissionResDTO.MissionItem toMissionItem(ActivatedMission am) {
        String stateStr = am.getState() == MissionState.ONGOING ? "진행중" : "진행완료";
        return MissionResDTO.MissionItem.builder()
                .activatedMissionId(String.valueOf(am.getId()))
                .missionId(String.valueOf(am.getMission().getId()))
                .storeName(am.getMission().getStore().getName())
                .minPaymentAmount(am.getMission().getMinPaymentAmount())
                .rewardPoints(am.getMission().getRewardPoints())
                .state(stateStr)
                .deadline(am.getMission().getDeadline())
                .build();
    }

    public static MissionResDTO.MissionPageResult toMissionPageResult(List<ActivatedMission> list, int limit) {
        boolean hasNext = list.size() == limit;
        String nextCursor = list.isEmpty() ? null : String.valueOf(list.get(list.size() - 1).getId());
        return MissionResDTO.MissionPageResult.builder()
                .missions(list.stream().map(MissionConverter::toMissionItem).toList())
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .build();
    }

    public static MissionResDTO.AvailableMissionItem toAvailableMissionItem(Mission mission) {
        long dDay = ChronoUnit.DAYS.between(LocalDate.now(), mission.getDeadline());
        return MissionResDTO.AvailableMissionItem.builder()
                .missionId(String.valueOf(mission.getId()))
                .storeName(mission.getStore().getName())
                .storeId(String.valueOf(mission.getStore().getId()))
                .foodType(mission.getStore().getFoodType())
                .minPaymentAmount(mission.getMinPaymentAmount())
                .rewardPoints(mission.getRewardPoints())
                .deadline(mission.getDeadline())
                .dDay(dDay)
                .distanceKm(0.0)
                .build();
    }

    public static MissionResDTO.AvailableMissionPageResult toAvailableMissionPageResult(List<Mission> list, int limit) {
        boolean hasNext = list.size() == limit;
        String nextCursor = list.isEmpty() ? null : String.valueOf(list.get(list.size() - 1).getId());
        return MissionResDTO.AvailableMissionPageResult.builder()
                .missions(list.stream().map(MissionConverter::toAvailableMissionItem).toList())
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .build();
    }

    public static MissionResDTO.StoreMissionItem toStoreMissionItem(Mission mission) {
        return MissionResDTO.StoreMissionItem.builder()
                .missionId(String.valueOf(mission.getId()))
                .description(mission.getDescription())
                .minPaymentAmount(mission.getMinPaymentAmount())
                .rewardPoints(mission.getRewardPoints())
                .deadline(mission.getDeadline())
                .build();
    }

    public static MissionResDTO.StoreMissionPageResult toStoreMissionPageResult(List<Mission> list, int limit) {
        boolean hasNext = list.size() == limit;
        String nextCursor = list.isEmpty() ? null : String.valueOf(list.get(list.size() - 1).getId());
        return MissionResDTO.StoreMissionPageResult.builder()
                .missions(list.stream().map(MissionConverter::toStoreMissionItem).toList())
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .build();
    }
}
