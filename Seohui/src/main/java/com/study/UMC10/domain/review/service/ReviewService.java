package com.study.UMC10.domain.review.service;

import com.study.UMC10.domain.review.dto.request.ReviewRequestDto;
import com.study.UMC10.domain.review.dto.response.ReviewResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    // 리뷰 작성
    public ReviewResponseDto.CreateReviewResultDto createReview(Long storeId, ReviewRequestDto.CreateReviewDto requestDto) {
        return null;
    }
}
