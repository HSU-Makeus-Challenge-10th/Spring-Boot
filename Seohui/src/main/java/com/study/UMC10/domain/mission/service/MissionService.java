package com.study.UMC10.domain.mission.service;

import com.study.UMC10.domain.mission.converter.MissionConverter;
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
    public MissionResponseDto.Pagination<MissionResponseDto.MissionDetailDto> getMissions(
            Long userId, String status, Integer pageSize, Integer pageNumber) {

        MissionStatus missionStatus = MissionStatus.valueOf(status.toUpperCase());

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        Page<UserMission> userMissionPage = userMissionRepository.findMyMissions(userId, missionStatus, pageRequest);

        List<MissionResponseDto.MissionDetailDto> missionDetailDtoList = userMissionPage.stream()
                .map(userMission -> MissionResponseDto.MissionDetailDto.builder()
                        .missionId(userMission.getMission().getId())
                        .rewardPoints(userMission.getMission().getRewardPoint())
                        .storeName(userMission.getMission().getStore().getStoreName())
                        .description(userMission.getMission().getMissionContent())
                        .status(userMission.getMissionStatus().name())
                        .build())
                .collect(Collectors.toList());

        return MissionConverter.toPagination(
                missionDetailDtoList,
                userMissionPage.getNumber(),
                userMissionPage.getSize()
        );
    }

    /*
     * NOTE:
     * 아직 구현 안함
     */
    public MissionResponseDto.MissionCompleteResultDto completeMission(Long missionId) {
        return null;
    }
}