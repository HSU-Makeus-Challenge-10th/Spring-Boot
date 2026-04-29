package com.study.UMC10.domain.review.controller;

import com.study.UMC10.domain.review.code.ReviewSuccessCode;
import com.study.UMC10.domain.review.dto.request.ReviewRequestDto;
import com.study.UMC10.domain.review.dto.response.ReviewResponseDto;
import com.study.UMC10.domain.review.service.ReviewService;
import com.study.UMC10.global.apiPayload.ApiResponse;
import com.study.UMC10.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 작성
    @PostMapping("/v1/stores/{storeId}/reviews")
    public ApiResponse<ReviewResponseDto.CreateReviewResultDto> createReview(
            @PathVariable("storeId") Long storeId,
            @RequestBody ReviewRequestDto.CreateReviewDto requestDto
    ) {
        BaseSuccessCode code = ReviewSuccessCode.REVIEW_CREATED;

        return ApiResponse.onSuccess(code, reviewService.createReview(storeId, requestDto));
    }
}