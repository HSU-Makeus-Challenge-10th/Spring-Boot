package com.study.UMC10.domain.mission.service;

import com.study.UMC10.domain.mission.dto.response.MissionResponseDto;
import com.study.UMC10.domain.mission.entity.UserMission;
import com.study.UMC10.domain.mission.enums.MissionStatus;
import com.study.UMC10.domain.mission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final UserMissionRepository userMissionRepository;

    @Transactional(readOnly = true)
    public MissionResponseDto.MissionListDto getMissions(String status, Integer page) {

        // 임시 유저
        Long dummyUserId = 1L;

        MissionStatus missionStatus = MissionStatus.valueOf(status.toUpperCase());

        // 페이징
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<UserMission> userMissionPage = userMissionRepository.findMyMissions(dummyUserId, missionStatus, pageRequest);

        List<MissionResponseDto.MissionDetailDto> missionDetailDtoList = userMissionPage.stream()
                .map(userMission -> MissionResponseDto.MissionDetailDto.builder()
                        .missionId(userMission.getMission().getId())
                        .rewardPoints(userMission.getMission().getRewardPoint())
                        .storeName(userMission.getMission().getStore().getStoreName())
                        .description(userMission.getMission().getMissionContent())
                        .status(userMission.getMissionStatus().name())
                        .build())
                .collect(Collectors.toList());

        return MissionResponseDto.MissionListDto.builder()
                .missions(missionDetailDtoList)
                .build();
    }

    // 아직 구현Xx
    public MissionResponseDto.MissionCompleteResultDto completeMission(Long missionId) {
        return null;
    }
}