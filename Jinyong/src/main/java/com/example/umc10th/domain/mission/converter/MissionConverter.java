package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.store.entity.Store;

import java.util.List;

public class MissionConverter {

    // 가게 미션 생성
    public static Mission toMission(
            Store store,
            MissionReqDTO.CreateMission dto
    ) {
        return Mission.builder()
                .store(store)
                .title(dto.title())
                .reward(dto.reward())
                .deadline(dto.deadline())
                .build();
    }

    // 가격 내 미션 조회
    public static MissionResDTO.GetMission toGetMission(
            Mission mission
    ){
        return MissionResDTO.GetMission.builder()
                .id(mission.getId())
                .storeId(mission.getStore().getId())
                .title(mission.getTitle())
                .reward(mission.getReward())
                .deadline(mission.getDeadline())
                .build();
    }

    // 내가 진행 중인 미션 조회
    public static MissionResDTO.MyOngoingMission toMyOngoingMission(
            MemberMission memberMission
    ) {
        Mission mission = memberMission.getMission();

        return MissionResDTO.MyOngoingMission.builder()
                .memberMissionId(memberMission.getId())
                .missionId(mission.getId())
                .storeId(mission.getStore().getId())
                .title(mission.getTitle())
                .deadline(mission.getDeadline())
                .reward(mission.getReward())
                .status(memberMission.getStatus())
                .build();
    }

    // 페이지네이션 툴 생성
    public static <T> MissionResDTO.Pagination<T> toPagination(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){
        return MissionResDTO.Pagination.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }
}
