package com.example.umc10thweek4.domain.mission.controller;

import com.example.umc10thweek4.domain.mission.dto.MissionReqDTO;
import com.example.umc10thweek4.domain.mission.dto.MissionResDTO;
import com.example.umc10thweek4.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10thweek4.domain.mission.service.MissionService;
import com.example.umc10thweek4.global.apiPayload.ApiResponse;
import com.example.umc10thweek4.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10thweek4.global.security.util.SecurityUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/v1/home")
    public ResponseEntity<ApiResponse<MissionResDTO.Home>> getHome(
            @RequestParam(required = false) Long regionId,
            @RequestParam Long memberId
    ) {
        return ApiResponse.onSuccessResponse(MissionSuccessCode.HOME_SUCCESS,
                missionService.getHomeMissions(regionId, memberId));
    }

    @GetMapping("/v1/home/me")
    public ResponseEntity<ApiResponse<MissionResDTO.Home>> getMyHome(
            @RequestParam(required = false) Long regionId
    ) {
        Long currentMemberId = SecurityUtil.getCurrentMemberId();
        return ApiResponse.onSuccessResponse(MissionSuccessCode.HOME_SUCCESS,
                missionService.getHomeMissions(regionId, currentMemberId));
    }

    @GetMapping("/v1/users/me/missions")
    public ResponseEntity<ApiResponse<MissionResDTO.Pagination<MissionResDTO.UserMissionList.UserMission>>> getMyMissions(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String sort) {

        Long currentMemberId = SecurityUtil.getCurrentMemberId();

        return ApiResponse.onSuccessResponse(MissionSuccessCode.MY_MISSION_SUCCESS,
                missionService.getMyMissions(currentMemberId, pageSize, pageNumber, sort));
    }

    @GetMapping("/v1/users/{userId}/missions")
    public ResponseEntity<ApiResponse<MissionResDTO.Pagination<MissionResDTO.UserMissionList.UserMission>>> getMyMissions(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String sort) {

        return ApiResponse.onSuccessResponse(MissionSuccessCode.MY_MISSION_SUCCESS,
                missionService.getMyMissions(userId, pageSize, pageNumber, sort));
    }

    @GetMapping("/v1/missions/{missionId}")
    public ResponseEntity<ApiResponse<Object>> getMissionDetail(@PathVariable Long missionId) {
        return ApiResponse.onSuccessResponse(MissionSuccessCode.MISSION_DETAIL_SUCCESS, null);
    }

    // 가게 미션 설정
    @PostMapping("/v1/stores/{storeId}/missions")
    public ResponseEntity<ApiResponse<Void>> createStoreMission(@PathVariable Long storeId, @RequestBody @Valid MissionReqDTO.CreateMission missionReqDTO) {
        BaseSuccessCode code = MissionSuccessCode.CREATED;
        return ApiResponse.onSuccessResponse(code, missionService.createMission(storeId, missionReqDTO));
    }

    // 가게 내 미션들 조회
    @GetMapping("/v1/stores/{storeId}/missions")
    public ResponseEntity<ApiResponse<List<MissionResDTO.GetMission>>> getStoreMissions(@PathVariable Long storeId) {
        BaseSuccessCode code = MissionSuccessCode.STORE_MISSION_SUCCESS;
        return ApiResponse.onSuccessResponse(code, missionService.getStoreMissions(storeId));
    }
}
