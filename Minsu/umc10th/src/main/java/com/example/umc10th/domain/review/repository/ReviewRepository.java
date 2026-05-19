package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @EntityGraph(attributePaths = {"store"})
    Slice<Review> findByMember_IdOrderByIdDesc(Long memberId, Pageable pageable);

    @EntityGraph(attributePaths = {"store"})
    Slice<Review> findByMember_IdAndIdLessThanOrderByIdDesc(Long memberId, Long idCursor, Pageable pageable);

    @EntityGraph(attributePaths = {"store"})
    Slice<Review> findByMember_IdOrderByRatingDescIdDesc(Long memberId, Pageable pageable);

    @Query("""
            SELECT r FROM Review r
            JOIN FETCH r.store
            WHERE r.member.id = :memberId
              AND (r.rating < :prevRating
                   OR (r.rating = :prevRating AND r.id < :idCursor))
            ORDER BY r.rating DESC, r.id DESC
            """)
    Slice<Review> findByRatingCursor(
            @Param("memberId") Long memberId,
            @Param("prevRating") Double prevRating,
            @Param("idCursor") Long idCursor,
            Pageable pageable
    );

    @Query("""
            SELECT DISTINCT r FROM Review r
            JOIN FETCH r.member
            LEFT JOIN FETCH r.comment
            WHERE r.store.id = :storeId
              AND r.id > :cursor
            ORDER BY r.id ASC
            """)
    List<Review> findByStoreIdWithDetailsCursor(
            @Param("storeId") Long storeId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );
}
