package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;

public class MissionConverter {

    public static MissionResDTO.MissionInfo toMissionInfo(Mission mission) {
        return MissionResDTO.MissionInfo.builder()
                .id(mission.getId())
                .storeId(mission.getStore().getId())
                .title(mission.getTitle())
                .deadline(mission.getDeadline())
                .reward(mission.getReward())
                .build();
    }
}