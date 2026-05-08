package com.umc.study.domain.review.web.controller;

import com.umc.study.domain.review.exception.code.ReviewSuccessCode;
import com.umc.study.domain.review.service.ReviewService;
import com.umc.study.domain.review.web.dto.CreateReviewReq;
import com.umc.study.domain.review.web.dto.ReviewDetail;
import com.umc.study.global.apiPayload.ApiResponse;
import com.umc.study.global.apiPayload.cursor.CursorRes;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    public ResponseEntity<ApiResponse<?>> getReviews(
            @PathVariable Long userId,
            @RequestParam Integer pageSize,
            @RequestParam String cursor,
            @RequestParam String query
    ) {
        if(pageSize == null || cursor == null || query == null
        || cursor.isBlank() || query.isBlank()) {
            throw new IllegalArgumentException("필수 필드가 누락되어 있습니다.");
        }
        if(pageSize <=0)
            throw new IllegalArgumentException("pageSize 값은 0 이하일 수 없습니다.");

        CursorRes<ReviewDetail, Long> response = reviewService.getAllMyReviews(userId, extract(cursor), query, pageSize);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.onComplete(ReviewSuccessCode.MY_REVIEW_OK, response));
    }

    private String[] extract(String cursor) {
        return cursor.split(":");
    }
}
