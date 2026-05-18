package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.dto.CursorPageRes;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/members")
public class ReviewController {

    private final ReviewService reviewService;

    // 내가 생성한 리뷰 목록 조회 (커서 기반 페이지네이션, ID순 / 별점순)
    @GetMapping("/{memberId}/reviews")
    public ApiResponse<CursorPageRes<ReviewResDTO.ReviewItem>> getMyReviews(
            @PathVariable Long memberId,
            @RequestParam(required = false) Long cursor,
            @RequestParam(required = false) Double ratingCursor,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_LIST,
                reviewService.getMyReviews(memberId, cursor, ratingCursor, sort, size));
    }
}
