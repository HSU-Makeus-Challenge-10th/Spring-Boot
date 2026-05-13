package com.example.umc10thweek4.domain.review.repository;

import com.example.umc10thweek4.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findByIdAndDeletedAtIsNull(Long id);

    // 특정 UserMission에 대한 리뷰 조회
    Optional<Review> findByUserMissionIdAndDeletedAtIsNull(Long userMissionId);

    // 특정 회원이 작성한 리뷰 목록
    List<Review> findByMemberIdAndDeletedAtIsNull(Long memberId);

    // ID 순 (최신순)
    List<Review> findByMemberIdAndDeletedAtIsNullOrderByIdDesc(Long memberId);

    // 특정 가게의 리뷰 목록
    @Query("SELECT r FROM Review r WHERE r.userMission.mission.store.id = :storeId AND r.deletedAt IS NULL")
    List<Review> findByStoreId(@Param("storeId") Long storeId);

    // 내가 작성한 리뷰 목록(최신순)
    @Query("SELECT r FROM Review r " +
            "WHERE r.member.id = :memberId " +
            "AND r.deletedAt IS NULL " +
            "AND (:cursorId IS NULL OR r.id < :cursorId) " +
            "ORDER BY r.id DESC")
    Slice<Review> findMyReviews(
            @Param("memberId") Long memberId,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );

    // 내가 작성한 리뷰 목록(별점 높은 순)
    @Query("""
            SELECT r FROM Review r
            WHERE r.member.id = :memberId
              AND r.deletedAt IS NULL
              AND (
                  :cursorId IS NULL
                  OR r.rating < (SELECT c.rating FROM Review c WHERE c.id = :cursorId)
                  OR (r.rating = (SELECT c.rating FROM Review c WHERE c.id = :cursorId)
                      AND r.id < :cursorId)
              )
            ORDER BY r.rating DESC, r.id DESC
            """)
    Slice<Review> findMyReviewsOrderByRating(
            @Param("memberId") Long memberId,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );
}