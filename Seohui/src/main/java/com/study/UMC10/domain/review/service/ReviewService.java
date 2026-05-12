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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    @Transactional
    public ReviewResponseDto.CreateReviewResultDto createReview(Long storeId, ReviewRequestDto.CreateReviewDto requestDto) {

        /*
         * NOTE:
         * 임시 유저로 하드 코딩
         * 추후 로그인 구현 후 수정 예정
         */
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

    // 내 리뷰 목록 조회
    @Transactional(readOnly = true)
    public ReviewResponseDto.CursorPagination<ReviewResponseDto.MyReviewDto> getMyReviews(
            Long userId, String query, String cursor, Integer pageSize) {

        PageRequest pageRequest = PageRequest.of(0, pageSize);
        Slice<Review> reviewSlice;

        if (query.equalsIgnoreCase("rate")) {
            // 별점 순
            if (cursor.equals("-1")) {
                reviewSlice = reviewRepository.findMyReviewsOrderByScoreDesc(userId, pageRequest);
            } else {
                String[] cursorSplit = cursor.split(":");
                Double scoreCursor = Double.parseDouble(cursorSplit[0]);
                Long idCursor = Long.parseLong(cursorSplit[1]);
                reviewSlice = reviewRepository.findMyReviewsByCursorScoreAndId(userId, scoreCursor, idCursor, pageRequest);
            }
        } else {
            // 리뷰 ID 순
            if (cursor.equals("-1")) {
                reviewSlice = reviewRepository.findMyReviewsOrderByIdDesc(userId, pageRequest);
            } else {
                Long idCursor = Long.parseLong(cursor);
                reviewSlice = reviewRepository.findMyReviewsByCursorId(userId, idCursor, pageRequest);
            }
        }

        List<ReviewResponseDto.MyReviewDto> dtoList = reviewSlice.getContent().stream()
                .map(review -> ReviewResponseDto.MyReviewDto.builder()
                        .reviewId(review.getId())
                        .storeName(review.getStore().getStoreName())
                        .nickname(review.getUser().getNickname())
                        .score(review.getScore())
                        .createdAt(review.getCreatedAt() != null ? review.getCreatedAt().toLocalDate() : null)
                        .ownerComment(review.getOwnerComment() != null ? review.getOwnerComment().getOwnerCommentContent() : null)
                        .ownerCommentCreatedAt(review.getOwnerComment() != null && review.getOwnerComment().getCreatedAt() != null ? review.getOwnerComment().getCreatedAt().toLocalDate() : null)
                        .build())
                .collect(Collectors.toList());

        // 다음 커서 계산
        String nextCursor = "-1";
        if (reviewSlice.hasNext() && !reviewSlice.getContent().isEmpty()) {
            Review lastReview = reviewSlice.getContent().get(reviewSlice.getContent().size() - 1);
            if (query.equalsIgnoreCase("rate")) {
                // 별점 순 커서: (별점:리뷰ID) .. 별점 같을 시 리뷰 ID순
                nextCursor = lastReview.getScore() + ":" + lastReview.getId();
            } else {
                // 최신순 커서: 리뷰ID
                nextCursor = String.valueOf(lastReview.getId());
            }
        }

        return ReviewResponseDto.CursorPagination.<ReviewResponseDto.MyReviewDto>builder()
                .data(dtoList)
                .hasNext(reviewSlice.hasNext())
                .nextCursor(nextCursor)
                .pageSize(reviewSlice.getSize())
                .build();
    }
}