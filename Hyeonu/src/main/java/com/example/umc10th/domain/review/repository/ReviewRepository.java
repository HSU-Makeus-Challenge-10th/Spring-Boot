package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // ── ID 순 ────────────────────────────────────────────────────────────────

    // 첫 페이지: ID 내림차순 전체 조회
    @Query("""
        SELECT r FROM Review r
        WHERE r.member.id = :memberId
        ORDER BY r.id DESC
        """)
    Slice<Review> findReviewsByMember_IdOrderByIdDesc(
            @Param("memberId") Long memberId,
            Pageable pageable);

    // 이후 페이지: 커서(ID)보다 작은 것만 조회
    @Query("""
        SELECT r FROM Review r
        WHERE r.member.id = :memberId
          AND r.id < :idCursor
        ORDER BY r.id DESC
        """)
    Slice<Review> findReviewsByMember_IdAndIdLessThanOrderByIdDesc(
            @Param("memberId") Long memberId,
            @Param("idCursor") Long idCursor,
            Pageable pageable);

    // ── RATING 순 ────────────────────────────────────────────────────────────

    // 첫 페이지: rating 내림차순, 동률이면 ID 내림차순
    @Query("""
        SELECT r FROM Review r
        WHERE r.member.id = :memberId
        ORDER BY r.rating DESC, r.id DESC
        """)
    Slice<Review> findReviewsByMember_IdOrderByRatingDescIdDesc(
            @Param("memberId") Long memberId,
            Pageable pageable);

    // 이후 페이지: (rating < cursor) OR (rating = cursor AND id < idCursor)
    @Query("""
        SELECT r FROM Review r
        WHERE r.member.id = :memberId
          AND (
                r.rating < :ratingCursor
                OR (r.rating = :ratingCursor AND r.id < :idCursor)
              )
        ORDER BY r.rating DESC, r.id DESC
        """)
    Slice<Review> findReviewsByMember_IdWithRatingCursor(
            @Param("memberId")     Long memberId,
            @Param("ratingCursor") Float ratingCursor,
            @Param("idCursor")     Long idCursor,
            Pageable pageable);
}