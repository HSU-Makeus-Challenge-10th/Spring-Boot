package com.umc.study.domain.review.web.controller;

import com.umc.study.domain.review.exception.code.ReviewSuccessCode;
import com.umc.study.domain.review.service.ReviewService;
import com.umc.study.domain.review.web.dto.CreateReviewReq;
import com.umc.study.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{userId}")
    public ResponseEntity<ApiResponse<?>> createReview(
            // extract by JWT in Security Context Holder
            @PathVariable Long userId,
            @Valid @RequestBody CreateReviewReq request
    ) {
        // call Service method
        reviewService.createReview(userId, request);

        return ResponseEntity
                .status(ReviewSuccessCode.REVIEW_CREATED.getStatus())
                .body(ApiResponse.onComplete(ReviewSuccessCode.REVIEW_CREATED, null));
    }
}
