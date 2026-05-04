package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
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

    // 리뷰 등록
    @PostMapping("/{missionId}/reviews")
    public ApiResponse<MissionResDTO.CreateReviewRes> createReview(
            @PathVariable Long missionId,
            @RequestParam Long memberId,  // TODO: 인증 구현 후 SecurityContext로 대체
            @Valid @RequestPart MissionReqDTO.CreateReviewReq request,
            @RequestPart(required = false) List<MultipartFile> reviewImages
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.REVIEW_CREATED,
                missionService.createReview(memberId, missionId, request, reviewImages));
    }
}
