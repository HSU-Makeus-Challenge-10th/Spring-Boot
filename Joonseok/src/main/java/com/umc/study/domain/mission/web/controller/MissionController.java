package com.umc.study.domain.mission.web.controller;

import com.umc.study.domain.mission.enums.code.MissionSuccessCode;
import com.umc.study.domain.mission.service.MissionService;
import com.umc.study.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getMyMissions(
            // JWT Security Context Holder
    ) {
        // call Service method

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
