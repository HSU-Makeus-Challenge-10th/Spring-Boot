package com.example.umc10thweek4.domain.review.controller;

import com.example.umc10thweek4.domain.review.dto.ReviewReqDTO;
import com.example.umc10thweek4.domain.review.dto.ReviewResDTO;
import com.example.umc10thweek4.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10thweek4.domain.review.service.ReviewService;
import com.example.umc10thweek4.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/v1/stores/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.Create> createReview(
            @PathVariable Long storeId,
            @RequestBody @Valid ReviewReqDTO.Create request) {

        Long currentMemberId = 1L;   // 임시 값

        Long userMissionId = 1L;     // 임시 값

        ReviewResDTO.Create response = reviewService.createReview(currentMemberId, request, userMissionId);

        return ApiResponse.onSuccess(ReviewSuccessCode.OK, response);
    }

    @GetMapping("/v1/users/{userId}/reviews")
    public ApiResponse<ReviewResDTO.Pagination<ReviewResDTO.GetReviewList>> getMyReviews(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String cursor,
            @RequestParam(required = false) ReviewReqDTO.SortType sort) {   // cursor = "reviewId:createdAt" 형태

        return ApiResponse.onSuccess(ReviewSuccessCode.OK,
                reviewService.getMyReviews(userId, pageSize, cursor, sort));
    }
}