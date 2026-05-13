package com.example.umc10thweek4.domain.mission.controller;

import com.example.umc10thweek4.domain.mission.dto.MissionReqDTO;
import com.example.umc10thweek4.domain.mission.dto.MissionResDTO;
import com.example.umc10thweek4.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10thweek4.domain.mission.service.MissionService;
import com.example.umc10thweek4.global.apiPayload.ApiResponse;
import com.example.umc10thweek4.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/v1/home")
    public ApiResponse<MissionResDTO.Home> getHome(
            @RequestParam(required = false) Long regionId,
            @RequestParam Long memberId
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.HOME_SUCCESS,
                missionService.getHomeMissions(regionId, memberId));
    }

    @GetMapping("/v1/users/{userId}/missions")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.UserMissionList.UserMission>> getMyMissions(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String sort) {

        return ApiResponse.onSuccess(MissionSuccessCode.MY_MISSION_SUCCESS,
                missionService.getMyMissions(userId, pageSize, pageNumber, sort));
    }

    @GetMapping("/v1/missions/{missionId}")
    public ApiResponse<Object> getMissionDetail(@PathVariable Long missionId) {
        return ApiResponse.onSuccess(MissionSuccessCode.OK, null);
    }

    // 가게 미션 설정
    @PostMapping("/v1/stores/{storeId}/missions")
    public ApiResponse<Void> createStoreMission(@PathVariable Long storeId, @RequestBody @Valid MissionReqDTO.CreateMission missionReqDTO) {
        BaseSuccessCode code = MissionSuccessCode.CREATED;
        return ApiResponse.onSuccess(code, missionService.createMission(storeId, missionReqDTO));
    }

    // 가게 내 미션들 조회
    @GetMapping("/v1/stores/{storeId}/missions")
    public ApiResponse<List<MissionResDTO.GetMission>> getStoreMissions(@PathVariable Long storeId) {
        BaseSuccessCode code = MissionSuccessCode.STORE_MISSION_SUCCESS;
        return ApiResponse.onSuccess(code, missionService.getStoreMissions(storeId));
    }
}