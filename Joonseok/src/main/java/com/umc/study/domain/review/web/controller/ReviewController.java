package com.umc.study.domain.review.web.controller;

import com.umc.study.domain.review.enums.ReviewSuccessCode;
import com.umc.study.domain.review.service.ReviewService;
import com.umc.study.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createReview(
            // extract by JWT in Security Context Holder
            @Valid @RequestBody Object request
    ) {
        // call Service method

        return ResponseEntity
                .status(ReviewSuccessCode.REVIEW_CREATED.getStatus())
                .body(ApiResponse.onComplete(ReviewSuccessCode.REVIEW_CREATED, null));
    }
}
