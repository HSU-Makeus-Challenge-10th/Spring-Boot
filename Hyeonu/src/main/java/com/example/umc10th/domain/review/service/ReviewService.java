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
import org.springframework.data.domain.Slice;
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


    // 리뷰 조회
    public ReviewResDTO.Pagination<ReviewResDTO.MyReviewDetailDTO> getReviews(
            Long memberId,
            Integer pageSize,
            String cursor,
            String query
    ) {
        PageRequest pageRequest = PageRequest.of(0, pageSize);

        Slice<Review> reviewList;
        String nextCursor = null;

        // 커서가 존재하는 경우 (첫 페이지가 아닌 경우)
        if (cursor != null && !cursor.equals("-1")) {

            // 커서 파싱: "firstId:lastId" 또는 "firstRating:lastId" 형태
            String[] cursorSplit = cursor.split(":");

            switch (query.toLowerCase()) {

                // ── ID 순 정렬 ──────────────────────────────────────────────────
                case "id":
                    // cursorSplit[1]: 이전 페이지 마지막 리뷰의 ID
                    long idCursor = Long.parseLong(cursorSplit[1]);

                    // 이전 페이지 마지막 ID보다 작은 리뷰들을 ID 내림차순으로 조회
                    reviewList = reviewRepository
                            .findReviewsByMember_IdAndIdLessThanOrderByIdDesc(
                                    memberId, idCursor, pageRequest);
                    break;

                // ── RATING 순 정렬 ───────────────────────────────────────────────
                case "rating":
                    // cursorSplit[0]: 이전 페이지 마지막 리뷰의 Rating
                    // cursorSplit[1]: 이전 페이지 마지막 리뷰의 ID (동일 rating 내 순서 보장용)
                    float ratingCursor = Float.parseFloat(cursorSplit[0]);
                    long ratingIdCursor = Long.parseLong(cursorSplit[1]);

                    // rating 내림차순, 같은 rating이면 ID 내림차순으로 조회
                    // WHERE (rating < ratingCursor) OR (rating = ratingCursor AND id < ratingIdCursor)
                    reviewList = reviewRepository
                            .findReviewsByMember_IdWithRatingCursor(
                                    memberId, ratingCursor, ratingIdCursor, pageRequest);
                    break;

                default:
                    throw new ReviewException(ReviewErrorCode.QUERY_NOT_VALID);
            }

        } else {
            // 커서가 없는 경우 (첫 페이지)
            switch (query.toLowerCase()) {

                // ── ID 순 첫 페이지 ──────────────────────────────────────────────
                case "id":
                    reviewList = reviewRepository
                            .findReviewsByMember_IdOrderByIdDesc(memberId, pageRequest);
                    break;

                // ── RATING 순 첫 페이지 ──────────────────────────────────────────
                case "rating":
                    reviewList = reviewRepository
                            .findReviewsByMember_IdOrderByRatingDescIdDesc(memberId, pageRequest);
                    break;

                default:
                    throw new ReviewException(ReviewErrorCode.QUERY_NOT_VALID);
            }
        }

        // 다음 페이지가 존재하고 현재 페이지 데이터가 있을 때만 커서 생성
        if (reviewList.hasNext() && !reviewList.getContent().isEmpty()) {
            Review lastReview = reviewList.getContent().getLast();

            switch (query.toLowerCase()) {

                // ID 순: "firstId:lastId"
                case "id":
                    nextCursor = reviewList.getContent().getFirst().getId()
                            + ":" + lastReview.getId();
                    break;

                // RATING 순: "lastRating:lastId"
                case "rating":
                    nextCursor = lastReview.getRating()
                            + ":" + lastReview.getId();
                    break;
            }
        }

        return ReviewConverter.toPagination(
                reviewList.map(ReviewConverter::toGetReview).toList(),
                reviewList.hasNext(),
                nextCursor,
                reviewList.getSize()
        );
    }
}
