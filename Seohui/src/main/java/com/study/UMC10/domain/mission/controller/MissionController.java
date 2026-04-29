package com.study.UMC10.domain.mission.controller;

import com.study.UMC10.domain.mission.code.MissionSuccessCode;
import com.study.UMC10.domain.mission.dto.response.MissionResponseDto;
import com.study.UMC10.domain.mission.service.MissionService;
import com.study.UMC10.global.apiPayload.ApiResponse;
import com.study.UMC10.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    // 미션 목록
    @GetMapping("/v1/missions")
    public ApiResponse<MissionResponseDto.MissionListDto> getMissions(
            @RequestParam("status") String status
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getMissions(status));
    }

    // 미션 성공 처리
    @PostMapping("/v1/missions/{missionId}/complete")
    public ApiResponse<MissionResponseDto.MissionCompleteResultDto> completeMission(
            @PathVariable("missionId") Long missionId
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.completeMission(missionId));
    }
}