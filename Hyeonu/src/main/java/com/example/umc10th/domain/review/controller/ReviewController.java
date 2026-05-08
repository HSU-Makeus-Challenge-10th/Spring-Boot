package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.apiPayload.code.MemberSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor // final이 붙은거 생성자 대신 작성
@RequestMapping("/api/v1")
public class ReviewController {
    private final ReviewService reviewService;

    // 특정 가게에 리뷰 등록
    @PostMapping("/stores/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReviewResult> createReview(
            @PathVariable Long storeId, // {storeId}에 적힌 숫자를 storeId라는 변수에 담음
            @RequestBody ReviewReqDTO.CreateReview dto // 사용자가 작성한 리뷰 정보 가져옴
    ) {
        BaseSuccessCode code=ReviewSuccessCode.OK;
        return ApiResponse.onSuccess(code, reviewService.createReviewResult(storeId,dto));
    }

}
