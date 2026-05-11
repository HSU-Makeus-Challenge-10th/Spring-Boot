package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.store.entity.Store;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResDTO.MissionDto toMissionDto(Mission mission) {
        return MissionResDTO.MissionDto.builder()
                .missionId(mission.getId())
                .title(mission.getTitle())
                .rewardPoint(mission.getPoint())
                .build();
    }

    public static List<MissionResDTO.MissionDto> toMissionDtoList(List<Mission> missions) {
        return missions.stream()
                .map(MissionConverter::toMissionDto)
                .collect(Collectors.toList());
    }

    //가게 미션 생성
    public static Mission toMission(
        Store store,
        MissionReqDTO.CreateMission dto
    ){
        return Mission.builder()
                .store(store)
                .conditional(dto.conditional())
                .point(dto.point())
                .deadline(dto.deadLine())
                .build();
    }
    //가게 내 미션 조회
    public static MissionResDTO.GetMission toGetMission(
            Mission mission
    ){
        return MissionResDTO.GetMission.builder()
                .conditional(mission.getConditional())
                .point(mission.getPoint())
                .missionId(mission.getId())
                .build();
    }

    //페이지 네이션 틀 생성
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
