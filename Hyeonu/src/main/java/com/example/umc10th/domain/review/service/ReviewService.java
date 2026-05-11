package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.exception.StoreException;
import com.example.umc10th.domain.store.exception.code.StoreErrorCode;
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

    // 리뷰 작성
    @Transactional
    public ReviewResDTO.CreateReviewResult createReviewResult(Long storeId, ReviewReqDTO.CreateReview dto) {
        // 리뷰를 달 가게가 있는지 확인
        Store store=storeRepository.findById(storeId)
                .orElseThrow(()->new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        // 리뷰를 쓰는 멤버가 있는지 확인
        Member member=memberRepository.findById(dto.memberId())
                .orElseThrow(()->new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 찾아온 가게와 멤버 정보를 엮어서 리뷰 객체 생성
        Review review=ReviewConverter.toReview(dto,member,store);

        // db저장
        Review savedReview=reviewRepository.save(review);

        return ReviewConverter.toCreateReviewResult(savedReview);
    }

}
