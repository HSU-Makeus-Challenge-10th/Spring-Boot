package com.study.UMC10.domain.mission.controller;

import com.study.UMC10.domain.mission.code.MissionSuccessCode;
import com.study.UMC10.domain.mission.dto.response.MissionResponseDto;
import com.study.UMC10.domain.mission.service.MissionService;
import com.study.UMC10.global.apiPayload.ApiResponse;
import com.study.UMC10.global.apiPayload.code.BaseSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Mission API", description = "미션 관련 API")
public class MissionController {

    private final MissionService missionService;

    @Operation(summary = "미션 목록 조회 API", description = "상태값에 따른 미션 목록을 조회하는 API입니다.")
    @Parameters({
            @Parameter(name = "MissionStatus", description = "조회할 미션 상태", example = "IN_PROGRESS, SUCCESS, FAILED"),
            @Parameter(name = "page", description = "페이지 번호", example = "0")
    })
    @GetMapping("/v1/missions")
    public ApiResponse<MissionResponseDto.MissionListDto> getMissions(
            @RequestParam("MissionStatus") String status,
            @RequestParam(name = "page", defaultValue = "0") Integer page
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getMissions(status, page));
    }

    @Operation(summary = "미션 완료 처리 API", description = "진행 중인 미션을 완료 상태로 변경하는 API입니다.")
    @Parameter(name = "missionId", description = "완료 처리할 미션 ID", example = "1")
    @PostMapping("/v1/missions/{missionId}/complete")
    public ApiResponse<MissionResponseDto.MissionCompleteResultDto> completeMission(
            @PathVariable("missionId") Long missionId
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.completeMission(missionId));
    }
}