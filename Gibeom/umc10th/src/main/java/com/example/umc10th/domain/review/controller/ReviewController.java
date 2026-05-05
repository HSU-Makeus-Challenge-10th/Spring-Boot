package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
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

    //유저의 모든 리뷰 가져오기
    @GetMapping("/v1/members/{memberId}/reviews")
    public ApiResponse<List<ReviewResDTO.reviewDTO>> getReviews(
            @PathVariable Long memberId) {
        BaseSuccessCode code = ReviewSuccessCode.OK;
        return ApiResponse.onSuccess(code, reviewService.getReviewList(memberId));
    }

}
