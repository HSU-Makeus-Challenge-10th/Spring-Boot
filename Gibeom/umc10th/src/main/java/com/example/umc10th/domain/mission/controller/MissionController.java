package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;


    //리뷰 작성 (완료된 미션에 한해서)
    @PostMapping("v1/missions/{missionId}/reviews")
    public ApiResponse<ReviewResDTO.WriteReviewResultDto> writeReview(
            @PathVariable Long missionId,
            @AuthenticationPrincipal Long memberId,
            @RequestBody ReviewReqDTO.WriteReviewDto dto) {
        BaseSuccessCode code = ReviewSuccessCode.WRITE_SUCCESS;
        return ApiResponse.onSuccess(code, missionService.writeReview(memberId, missionId, dto));
    }
}
