package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 전체 조회 API
    @GetMapping("/v1/reviews")
    public ApiResponse<List<ReviewResDTO.ReviewInfo>> getReviewList() {
        BaseSuccessCode code = ReviewSuccessCode.OK;
        return ApiResponse.onSuccess(code, reviewService.getReviewList());
    }

    // 내가 작성한 리뷰 조회 API - 커서 기반 페이지네이션
    @GetMapping("/v1/users/{memberId}/reviews")
    public ApiResponse<ReviewResDTO.CursorPagination<ReviewResDTO.MyReview>> getMyReviews(
            @PathVariable Long memberId,
            @RequestParam Integer pageSize,
            @RequestParam String cursor,
            @RequestParam String query
    ) {
        BaseSuccessCode code = ReviewSuccessCode.OK;

        return ApiResponse.onSuccess(
                code,
                reviewService.getMyReviews(memberId, pageSize, cursor, query)
        );
    }
}