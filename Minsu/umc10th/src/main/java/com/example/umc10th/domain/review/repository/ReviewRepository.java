package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId AND r.id > :cursor ORDER BY r.id ASC")
    List<Review> findByMemberIdCursor(@Param("memberId") Long memberId, @Param("cursor") Long cursor, Pageable pageable);

    @Query("SELECT r FROM Review r WHERE r.store.id = :storeId AND r.id > :cursor ORDER BY r.id ASC")
    List<Review> findByStoreIdCursor(@Param("storeId") Long storeId, @Param("cursor") Long cursor, Pageable pageable);
}
