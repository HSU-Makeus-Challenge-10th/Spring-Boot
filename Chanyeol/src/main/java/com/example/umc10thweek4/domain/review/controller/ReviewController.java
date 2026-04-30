package com.example.umc10thweek4.domain.review.controller;

import com.example.umc10thweek4.domain.review.dto.ReviewReqDTO;
import com.example.umc10thweek4.domain.review.dto.ReviewResDTO;
import com.example.umc10thweek4.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10thweek4.global.apiPayload.ApiResponse;
import com.example.umc10thweek4.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    @PostMapping("/v1/stores/{storeId}/reviews")
    public ApiResponse<ReviewResDTO> createReview(
            @PathVariable Long storeId,
            @RequestBody ReviewReqDTO dto) {

        BaseSuccessCode code = ReviewSuccessCode.OK;
        return ApiResponse.onSuccess(code, null);
    }
}