package com.study.UMC10.domain.mission.service;

import com.study.UMC10.domain.mission.dto.response.MissionResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionService {

    // 미션 목록 조회
    public MissionResponseDto.MissionListDto getMissions(String status) {
        return null;
    }

    // 미션 완료 처리
    public MissionResponseDto.MissionCompleteResultDto completeMission(Long missionId) {
        return null;
    }
}