package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    // 가게 미션 생성(POST)
    @PostMapping("/v1/stores/{storeId}/missions")
    public ApiResponse<Void> createMission(
            @PathVariable Long storeId,
            @RequestBody @Valid MissionReqDTO.CreateMission dto
    ) {
        missionService.createMission(storeId, dto);

        BaseSuccessCode code = MissionSuccessCode.CREATED;
        return ApiResponse.onSuccess(code, null);
    }

    // 가게 내 미션 목록 조회 조회(GET)
    @GetMapping("/v1/stores/{storeId}/missions")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.GetMission>> getMissionList(
            @PathVariable Long storeId,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getMissionList(storeId, pageSize, pageNumber, sort));
    }

    // 내가 진행 중인 미션 조회
    @GetMapping("/v1/users/{memberId}/missions/ongoing")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.MyOngoingMission>> getMyOngoingMissions(
            @PathVariable Long memberId,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;

        return ApiResponse.onSuccess(
                code,
                missionService.getMyOngoingMissions(memberId, pageSize, pageNumber, sort)
        );
    }
}
