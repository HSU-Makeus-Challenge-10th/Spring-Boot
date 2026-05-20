package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // ID 순 첫 조회
    @Query("SELECT r FROM Review r " +
            "WHERE r.member.id = :memberId " +
            "ORDER BY r.id DESC")
    Slice<Review> findMyReviewsByIdFirst(
            @Param("memberId") Long memberId,
            Pageable pageable
    );

    // ID 순 커서 조회
    @Query("SELECT r FROM Review r " +
            "WHERE r.member.id = :memberId " +
            "AND r.id < :cursorId " +
            "ORDER BY r.id DESC")
    Slice<Review> findMyReviewsByIdCursor(
            @Param("memberId") Long memberId,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );

    // 별점 순 첫 조회
    @Query("SELECT r FROM Review r " +
            "WHERE r.member.id = :memberId " +
            "ORDER BY r.score DESC, r.id DESC")
    Slice<Review> findMyReviewsByScoreFirst(
            @Param("memberId") Long memberId,
            Pageable pageable
    );

    // 별점 순 커서 조회
    @Query("SELECT r FROM Review r " +
            "WHERE r.member.id = :memberId " +
            "AND (r.score < :cursorScore " +
            "OR (r.score = :cursorScore AND r.id < :cursorId)) " +
            "ORDER BY r.score DESC, r.id DESC")
    Slice<Review> findMyReviewsByScoreCursor(
            @Param("memberId") Long memberId,
            @Param("cursorScore") Integer cursorScore,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );
}