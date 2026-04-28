package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Review", description = "리뷰 API")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/users/{userId}/reviews")
    @Operation(summary = "작성한 리뷰 전체 조회 (커서 페이징)")
    public ApiResponse<ReviewResDTO.MyReviewPageResult> getMyReviews(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") Long cursor,
            @RequestParam(defaultValue = "10") int limit) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, reviewService.getMyReviews(userId, cursor, limit));
    }

    @DeleteMapping("/reviews/{reviewId}")
    @Operation(summary = "리뷰 삭제")
    public ApiResponse<Void> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ApiResponse.onSuccess(ReviewSuccessCode.DELETED, null);
    }

    @PostMapping(value = "/stores/{storeId}/reviews", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "가게 리뷰 작성 (multipart/form-data)")
    public ApiResponse<ReviewResDTO.ReviewInfo> createReview(
            @PathVariable Long storeId,
            @RequestPart("request") ReviewReqDTO.CreateReview request,
            @RequestPart(value = "images", required = false) List<MultipartFile> images) {
        return ApiResponse.onSuccess(ReviewSuccessCode.CREATED, reviewService.createReview(storeId, request, images));
    }

    @GetMapping("/stores/{storeId}/reviews")
    @Operation(summary = "가게 리뷰 조회 (커서 페이징)")
    public ApiResponse<ReviewResDTO.StoreReviewPageResult> getStoreReviews(
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "0") Long cursor,
            @RequestParam(defaultValue = "10") int limit) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, reviewService.getStoreReviews(storeId, cursor, limit));
    }

    @GetMapping("/stores/{storeId}/reviews/images")
    @Operation(summary = "가게 리뷰 사진 조회 (커서 페이징)")
    public ApiResponse<ReviewResDTO.ReviewImagePageResult> getStoreReviewImages(
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "0") Long cursor,
            @RequestParam(defaultValue = "10") int limit) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, reviewService.getStoreReviewImages(storeId, cursor, limit));
    }
}
