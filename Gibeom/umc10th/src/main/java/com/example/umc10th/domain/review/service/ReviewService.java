package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewResDTO.Pagination<ReviewResDTO.getReview> getMemberReviewsOrderById(
            Long memberId,
            Integer pageSize,
            String cursor,
            String query
    ) {

        //페이지 정보 PageRequest만들기
        PageRequest pageRequest = PageRequest.of(0, pageSize);

        long idCursor;
        Slice<Review> reviewList;
        String nextCursor;

        //커서가 있는 경우
        if (!cursor.equals("-1")) {

            // 커서 분리
            String[] cursorSplit = cursor.split(":");
            switch (query.toLowerCase()) {
                case "id":
                    Long prevCursor = Long.parseLong(cursorSplit[0]); //이거 워크북 코드에 왜 있는지?
                    idCursor = Long.parseLong(cursorSplit[1]);
                    // 멤버의 리뷰들 조회 & where 절에 커서값 기입
                    reviewList = reviewRepository.findReviewsByMember_IdAndIdLessThanOrderByIdDesc(
                            memberId,
                            idCursor,
                            pageRequest
                    );
                    break;
                default:
                    throw new ReviewException(ReviewErrorCode.QUERY_NOT_VALID);
            }
        } else {
            //커서 없이 조회
            reviewList = reviewRepository.findReviewsByMember_IdOrderByIdDesc(memberId, pageRequest);
        }

        //다음 커서 계산 (마지막 요소의 id)
        nextCursor = reviewList.getContent().getLast().getId() + ":" + reviewList.getContent().getLast().getId();
        // 응답 DTO로 포장하기
        return ReviewConverter.toPagination(
                reviewList.map(ReviewConverter::toGetReview).toList(),
                reviewList.hasNext(),
                nextCursor,
                reviewList.getSize()
        );
    }
    //별점 순 페이징
    public ReviewResDTO.Pagination<ReviewResDTO.getReview> getMemberReviewsOrderByScore(
            Long memberId,
            Integer pageSize,
            String cursor,
            String query
    ) {
        PageRequest pageRequest = PageRequest.of(0, pageSize);
        Slice<Review> reviewList;
        String nextCursor;
        //커서가 있는 경우
        if (!cursor.equals("-1")) {
            // 커서 분리
            String[] cursorSplit = cursor.split(":");
            switch (query.toLowerCase()) {
                case "score":
                    // 커서 타입 변환
                    int scoreCursor = Integer.parseInt(cursorSplit[0]);

                    //리뷰들 조회 & where절에 커서 값 기입
                    reviewList = reviewRepository.findReviewsByScoreCursor(
                            memberId,
                            scoreCursor,
                            pageRequest);
                    break;
                default:
                    throw new ReviewException(ReviewErrorCode.QUERY_NOT_VALID);
            }
        } else {
            //커서 없이 조회
            reviewList = reviewRepository.findReviewsByMember_IdOrderByScoreDescIdDesc(memberId, pageRequest);
        }
        //다음 커서 계산
        nextCursor = reviewList.getContent().getLast().getId() + ":" + reviewList.getContent().getLast().getId();
        //응답 DTO로 포장하기
        return ReviewConverter.toPagination(
                reviewList.map(ReviewConverter::toGetReview).toList(),
                reviewList.hasNext(),
                nextCursor,
                reviewList.getSize()
        );
    }
}
