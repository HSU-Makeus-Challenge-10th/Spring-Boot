package com.example.umc10thweek4.domain.mission.controller;

import com.example.umc10thweek4.domain.mission.dto.MissionResDTO;
import com.example.umc10thweek4.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10thweek4.domain.mission.service.MissionService;
import com.example.umc10thweek4.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public ApiResponse<MissionResDTO.UserMissionList> getMyMissions(@PathVariable Long userId) {
        return ApiResponse.onSuccess(MissionSuccessCode.MY_MISSION_SUCCESS,
                missionService.getMyMissions(userId));
    }

    @GetMapping("/v1/missions/{missionId}")
    public ApiResponse<Object> getMissionDetail(@PathVariable Long missionId) {
        return ApiResponse.onSuccess(MissionSuccessCode.OK, null);
    }
}