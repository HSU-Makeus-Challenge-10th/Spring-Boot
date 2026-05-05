package com.umc.study.domain.mission.web.controller;

import com.umc.study.domain.mission.exception.code.MissionSuccessCode;
import com.umc.study.domain.mission.service.MissionService;
import com.umc.study.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/{userId}/complete")
    public ResponseEntity<ApiResponse<?>> getMyMissions(
            @PathVariable Long userId,
            @RequestParam int page
    ) {
        // call Service method
        if(page < 1)
            throw new IllegalArgumentException("page 값이 유효하지 않습니다.");

        return ResponseEntity
                .status(MissionSuccessCode.MISSION_GET_OK.getStatus())
                .body(ApiResponse.onComplete(MissionSuccessCode.MISSION_GET_OK, null));
    }

    @PostMapping("/success")
    public ResponseEntity<ApiResponse<?>> requestMissionSuccess(
            // JWT Security Context Holder
            @Valid @RequestBody Object request
    ) {

        // call Service method

        return ResponseEntity
                .status(MissionSuccessCode.MISSION_COMPLETE_REQUEST.getStatus())
                .body(ApiResponse.onComplete(MissionSuccessCode.MISSION_COMPLETE_REQUEST, null));
    }

    @PostMapping("/verification/{complCheckId}")
    public ResponseEntity<ApiResponse<?>> requestMissionVerification(
            @PathVariable Long complCheckId
    ) {
        // call Service method

        return ResponseEntity
                .status(MissionSuccessCode.MISSION_COMPLETED.getStatus())
                .body(ApiResponse.onComplete(MissionSuccessCode.MISSION_COMPLETED, null));
    }
}
