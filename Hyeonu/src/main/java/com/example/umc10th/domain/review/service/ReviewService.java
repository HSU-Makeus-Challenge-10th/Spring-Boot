package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public ReviewResDTO.CreateReviewResult createReview(Long storeId, ReviewReqDTO.CreateReview request) {
        Member member = memberRepository.findById(request.memberId()).orElseThrow();
        Store store = storeRepository.findById(storeId).orElseThrow();

        Review review = ReviewConverter.toReview(request, member, store);
        return ReviewConverter.toCreateReviewResult(reviewRepository.save(review));
    }
    // 특정 가게의 리뷰 목록 조회(페이징)
    public List<ReviewResDTO.ReviewDetail> getReviewList(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId).orElseThrow();

        // 1. 일단 페이지로 가져옵니다.
        Page<Review> reviewPage = reviewRepository.findAllByStoreId(storeId, PageRequest.of(page, 10));

        // 2. DTO로 변환한 뒤, .toList()를 써서 리스트로만 뽑아냅니다.
        return reviewPage.map(ReviewConverter::toReviewSummaryDTO).getContent();
    }
}
