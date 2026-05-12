package com.study.UMC10.domain.review.controller;

import com.study.UMC10.domain.review.code.ReviewSuccessCode;
import com.study.UMC10.domain.review.dto.request.ReviewRequestDto;
import com.study.UMC10.domain.review.dto.response.ReviewResponseDto;
import com.study.UMC10.domain.review.service.ReviewService;
import com.study.UMC10.global.apiPayload.ApiResponse;
import com.study.UMC10.global.apiPayload.code.BaseSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Operation(summary = "내가 작성한 리뷰 목록 조회 API", description = "커서 기반 페이징으로 내가 작성한 리뷰를 최신순/별점순으로 조회합니다.")
    @Parameters({
            @Parameter(name = "userId", description = "조회할 유저 ID", example = "1"),
            @Parameter(name = "query", description = "정렬 기준(최신순: id, 별점순: rate)", example = "id"),
            @Parameter(name = "cursor", description = "다음 페이지 커서/ 최초 요청 시 -1 입력", example = "-1"),
            @Parameter(name = "pageSize", description = "가져올 데이터 수", example = "10")
    })
    @GetMapping("/v1/reviews/me")
    public ApiResponse<ReviewResponseDto.CursorPagination<ReviewResponseDto.MyReviewDto>> getMyReviews(
            @RequestParam("userId") Long userId,
            @RequestParam(name = "query", defaultValue = "id") String query,
            @RequestParam(name = "cursor", defaultValue = "-1") String cursor,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        BaseSuccessCode code = com.study.UMC10.global.apiPayload.code.GeneralSuccessCode.OK;

        return ApiResponse.onSuccess(code, reviewService.getMyReviews(userId, query, cursor, pageSize));
    }
}