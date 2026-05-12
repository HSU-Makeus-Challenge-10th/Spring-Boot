package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<ReviewResDTO.ReviewInfo> getReviewList() {
        return reviewRepository.findAll().stream()
                .map(ReviewConverter::toReviewInfo)
                .toList();
    }

    // 가게 내 미션들 조회
    @Transactional
    public ReviewResDTO.CursorPagination<ReviewResDTO.MyReview> getMyReviews(
            Long memberId,
            Integer pageSize,
            String cursor,
            String query
    ) {
        // 페이지 정보들을 PageRequest로 만들기
        PageRequest pageRequest = PageRequest.of(0, pageSize);

        Slice<Review> reviewList;
        String nextCursor;

        // 커서가 있는 경우
        if (!cursor.equals("-1")) {

            String[] cursorSplit = cursor.split(":");

            switch (query.toLowerCase()) {

                // ID 순 조회
                case "id" -> {
                    Long idCursor = Long.parseLong(cursorSplit[0]);

                    reviewList = reviewRepository.findMyReviewsByIdCursor(
                            memberId,
                            idCursor,
                            pageRequest
                    );
                }

                // 별점 순 조회
                case "score" -> {
                    Integer scoreCursor = Integer.parseInt(cursorSplit[0]);
                    Long idCursor = Long.parseLong(cursorSplit[1]);

                    // 실제 DB 조회
                    reviewList = reviewRepository.findMyReviewsByScoreCursor(
                            memberId,
                            scoreCursor,
                            idCursor,
                            pageRequest
                    );
                }

                // 예외 처리
                default -> throw new IllegalArgumentException("query는 id 또는 score만 가능합니다.");
            }

        } else { // cursor = -1인 경우의 시작 (첫 조회)
            // 커서 없이 첫 조회
            switch (query.toLowerCase()) {

                // ID 순 첫 조회
                case "id" -> reviewList = reviewRepository.findMyReviewsByIdFirst(
                        memberId,
                        pageRequest
                );

                // 별점 순 첫 조회
                case "score" -> reviewList = reviewRepository.findMyReviewsByScoreFirst(
                        memberId,
                        pageRequest
                );

                default -> throw new IllegalArgumentException("query는 id 또는 score만 가능합니다.");
            }
        }

        // 다음 커서 계산
        if (reviewList.getContent().isEmpty()) {
            nextCursor = null; //  조회결과가 비어 있으면 다음 커서를 만들 수 없음
        } else {
            Review lastReview = reviewList.getContent()
                    .get(reviewList.getContent().size() - 1); //  마지막 데이터 꺼내오기


            // ID 기준이면 다음 커서는 마지막 리뷰의 ID
            if (query.equals("id")) {
                nextCursor = String.valueOf(lastReview.getId());
            } else { // 별점 기준이면 다음 커서는 score:id 형태
                nextCursor = lastReview.getScore() + ":" + lastReview.getId();
            }
        }

        // 리뷰 응답 DTO로 포장하기
        return ReviewConverter.toCursorPagination(
                reviewList.getContent().stream()
                        .map(ReviewConverter::toMyReview)
                        .toList(),
                reviewList.hasNext(),
                nextCursor,
                reviewList.getSize()
        );
    }
}