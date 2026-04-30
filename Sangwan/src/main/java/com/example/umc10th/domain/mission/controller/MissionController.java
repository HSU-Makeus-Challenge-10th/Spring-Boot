package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/missions")
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/{missionId}/reviews")
    public ApiResponse<MissionResDTO.CreateReviewRes> createReview(
            @PathVariable Long missionId,
            @Valid @RequestPart MissionReqDTO.CreateReviewReq request,
            @RequestPart(required = false) List<MultipartFile> reviewImages
    ) {
        BaseSuccessCode code = MissionSuccessCode.REVIEW_CREATED;
        return ApiResponse.onSuccess(code, missionService.createReview(missionId, request, reviewImages));
    }
}
