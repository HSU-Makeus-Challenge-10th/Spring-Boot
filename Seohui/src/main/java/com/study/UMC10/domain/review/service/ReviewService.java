package com.study.UMC10.domain.review.service;

import com.study.UMC10.domain.review.dto.request.ReviewRequestDto;
import com.study.UMC10.domain.review.dto.response.ReviewResponseDto;
import com.study.UMC10.domain.review.entity.Review;
import com.study.UMC10.domain.review.repository.ReviewRepository;
import com.study.UMC10.domain.store.entity.Store;
import com.study.UMC10.domain.store.repository.StoreRepository;
import com.study.UMC10.domain.user.entity.User;
import com.study.UMC10.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    @Transactional
    public ReviewResponseDto.CreateReviewResultDto createReview(Long storeId, ReviewRequestDto.CreateReviewDto requestDto) {

        // 임시 유저 (로그인 구현 X)
        Long dummyUserId = 1L;
        User user = userRepository.findById(dummyUserId)
                .orElseThrow(() -> new RuntimeException("해당 유저를 찾을 수 없습니다."));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("해당 가게를 찾을 수 없습니다."));

        Review review = Review.builder()
                .score(requestDto.rate())
                .reviewContent(requestDto.content())
                .user(user)
                .store(store)
                .reviewPhotoList(new ArrayList<>()) // 미션 사진 배제 -> 빈 리스트
                .build();

        Review savedReview = reviewRepository.save(review);

        return ReviewResponseDto.CreateReviewResultDto.builder()
                .reviewId(savedReview.getId())
                .storeId(store.getId())
                .rate(savedReview.getScore())
                .content(savedReview.getReviewContent())
                .images(new ArrayList<>())
                .build();
    }
}