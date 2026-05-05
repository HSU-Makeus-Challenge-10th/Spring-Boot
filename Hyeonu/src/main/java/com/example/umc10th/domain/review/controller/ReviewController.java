package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.MemberSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    // 특정 가게에 리뷰 등록
    @PostMapping("/stores/{storeId}")
    public ApiResponse<ReviewResDTO.CreateReviewResult> createReview(
            @PathVariable Long storeId,
            @RequestBody ReviewReqDTO.CreateReview request
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.OK, reviewService.createReview(storeId, request));
    }

    // 특정 가게의 리뷰 목록 조회(페이징 포함)
    @GetMapping("/stores/{storeId}")
    public ApiResponse<List<ReviewResDTO.ReviewDetail>>getStoreReviews(
            @PathVariable Long storeId,
            @RequestParam(name="page",defaultValue = "0")Integer page
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.OK, reviewService.getReviewList(storeId, page));
    }
}
