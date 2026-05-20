package com.study.UMC10.domain.review.repository;

import com.study.UMC10.domain.review.entity.Review;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 리뷰 ID 기준 내림차순
    // 커서 없는 최초 조회
    @Query("SELECT r FROM Review r WHERE r.user.id = :userId ORDER BY r.id DESC")
    Slice<Review> findMyReviewsOrderByIdDesc(@Param("userId") Long userId, Pageable pageable);

    // 커서 조회
    @Query("SELECT r FROM Review r WHERE r.user.id = :userId AND r.id < :cursorId ORDER BY r.id DESC")
    Slice<Review> findMyReviewsByCursorId(@Param("userId") Long userId, @Param("cursorId") Long cursorId, Pageable pageable);

    // 별점 순
    // 최초 조회
    @Query("SELECT r FROM Review r WHERE r.user.id = :userId ORDER BY r.score DESC, r.id DESC")
    Slice<Review> findMyReviewsOrderByScoreDesc(@Param("userId") Long userId, Pageable pageable);

    // 커서 조회
    @Query("SELECT r FROM Review r WHERE r.user.id = :userId AND (r.score < :cursorScore OR (r.score = :cursorScore AND r.id < :cursorId)) ORDER BY r.score DESC, r.id DESC")
    Slice<Review> findMyReviewsByCursorScoreAndId(@Param("userId") Long userId, @Param("cursorScore") Double cursorScore, @Param("cursorId") Long cursorId, Pageable pageable);
}