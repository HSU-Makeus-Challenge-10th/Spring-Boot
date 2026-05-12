package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc10th.global.dto.CommonResDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
@Tag(name = "Mission", description = "미션 API")
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/{activatedMissionId}/success")
    @Operation(summary = "미션 성공 요청")
    public ApiResponse<MissionResDTO.ActivatedMissionInfo> successMission(
            @PathVariable Long activatedMissionId,
            @RequestBody @Valid MissionReqDTO.SuccessRequest dto) {
        return ApiResponse.onSuccess(MissionSuccessCode.SUCCESS_REQUESTED, missionService.successMission(activatedMissionId, dto));
    }

    @GetMapping("/completed")
    @Operation(summary = "진행 완료 미션 조회 (커서 페이징)")
    public ApiResponse<MissionResDTO.MissionPageResult> getCompletedMissions(
            @RequestParam(defaultValue = "0") Long cursor,
            @RequestParam(defaultValue = "10") int limit) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missionService.getCompletedMissions(cursor, limit));
    }

    @GetMapping("/ongoing")
    @Operation(summary = "진행중 미션 조회 (커서 페이징)")
    public ApiResponse<MissionResDTO.MissionPageResult> getOngoingMissions(
            @RequestParam(defaultValue = "0") Long cursor,
            @RequestParam(defaultValue = "10") int limit) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missionService.getOngoingMissions(cursor, limit));
    }

    @PostMapping("/ongoing")
    @Operation(summary = "진행중 미션 조회 (오프셋 페이징)")
    public ApiResponse<CommonResDTO.OffsetPagination<MissionResDTO.MissionItem>> getOngoingMissionsByPage(
            @RequestBody @Valid MissionReqDTO.GetOngoingMissions dto) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missionService.getOngoingMissions(dto));
    }

    @GetMapping("/available")
    @Operation(summary = "도전 가능한 미션 조회 (커서 페이징)")
    public ApiResponse<MissionResDTO.AvailableMissionPageResult> getAvailableMissions(
            @RequestParam(required = false) Long townId,
            @RequestParam(defaultValue = "0") Long cursor,
            @RequestParam(defaultValue = "10") int limit) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missionService.getAvailableMissions(townId, cursor, limit));
    }

    @PostMapping("/{missionId}/start")
    @Operation(summary = "미션 도전 시작")
    public ApiResponse<MissionResDTO.ActivatedMissionInfo> startMission(@PathVariable Long missionId) {
        return ApiResponse.onSuccess(MissionSuccessCode.STARTED, missionService.startMission(missionId));
    }

    @GetMapping("/{activatedMissionId}/approver_code")
    @Operation(summary = "구분번호 요청")
    public ApiResponse<MissionResDTO.ApproverCodeInfo> getApproverCode(@PathVariable Long activatedMissionId) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missionService.getApproverCode(activatedMissionId));
    }
}
