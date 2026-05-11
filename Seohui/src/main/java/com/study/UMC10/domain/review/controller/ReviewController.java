package com.study.UMC10.domain.review.controller;

import com.study.UMC10.domain.review.code.ReviewSuccessCode;
import com.study.UMC10.domain.review.dto.request.ReviewRequestDto;
import com.study.UMC10.domain.review.dto.response.ReviewResponseDto;
import com.study.UMC10.domain.review.service.ReviewService;
import com.study.UMC10.global.apiPayload.ApiResponse;
import com.study.UMC10.global.apiPayload.code.BaseSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Review API", description = "리뷰 관련 API")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "가게 리뷰 작성 API", description = "특정 가게에 별점과 내용이 포함된 리뷰를 작성하는 API입니다.")
    @Parameter(name = "storeId", description = "리뷰를 작성할 가게의 ID", example = "15")
    @PostMapping("/v1/stores/{storeId}/reviews")
    public ApiResponse<ReviewResponseDto.CreateReviewResultDto> createReview(
            @PathVariable("storeId") Long storeId,
            @RequestBody ReviewRequestDto.CreateReviewDto requestDto
    ) {
        BaseSuccessCode code = ReviewSuccessCode.REVIEW_CREATED;

        return ApiResponse.onSuccess(code, reviewService.createReview(storeId, requestDto));
    }
}