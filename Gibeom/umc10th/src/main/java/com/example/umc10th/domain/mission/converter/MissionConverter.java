package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResDTO.MissionDto toMissionDto(Mission mission) {
        return MissionResDTO.MissionDto.builder()
                .missionId(mission.getId())
                .title(mission.getTitle())
                .rewardPoint(mission.getRewardPoint())
                .build();
    }

    public static List<MissionResDTO.MissionDto> toMissionDtoList(List<Mission> missions) {
        return missions.stream()
                .map(MissionConverter::toMissionDto)
                .collect(Collectors.toList());
    }


}
