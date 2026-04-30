package com.example.umc10thweek4.domain.mission.controller;

import com.example.umc10thweek4.domain.mission.dto.MissionResDTO;
import com.example.umc10thweek4.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10thweek4.global.apiPayload.ApiResponse;
import com.example.umc10thweek4.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    @GetMapping("/v1/home")
    public ApiResponse<MissionResDTO.Home> getHome() {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, null);
    }

    @GetMapping("/v1/user-missions")
    public ApiResponse<MissionResDTO.UserMissionList> getUserMissions(@RequestParam(required = false) String status) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, null);
    }

    @PatchMapping("/v1/user-missions/{userMissionId}/complete")
    public ApiResponse<MissionResDTO.Complete> completeMission(@PathVariable Long userMissionId) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, null);
    }
}