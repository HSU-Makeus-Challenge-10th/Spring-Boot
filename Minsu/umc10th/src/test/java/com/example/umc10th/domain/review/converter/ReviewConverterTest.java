package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.store.entity.Store;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ReviewConverterTest {

    @Test
    void myReviewCursorItemDoesNotExposeImages() {
        Store store = Store.builder()
                .id(1L)
                .name("반이학생마라탕")
                .build();
        Member member = Member.builder()
                .id(1L)
                .name("민수")
                .email("minsu@example.com")
                .build();
        Review review = Review.builder()
                .id(10L)
                .member(member)
                .store(store)
                .rating(4.5)
                .body("맛있어요")
                .build();

        ReviewResDTO.MyReviewCursorItem result = ReviewConverter.toMyReviewCursorItem(review);

        assertEquals("10", result.reviewId());
        assertEquals("반이학생마라탕", result.storeName());
        assertEquals(4.5, result.rating());
        assertEquals("맛있어요", result.body());
        assertNull(result.createdAt());
    }
}
