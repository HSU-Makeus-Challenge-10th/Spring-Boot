package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.ReviewImage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {

    @Query("SELECT ri FROM ReviewImage ri WHERE ri.review.store.id = :storeId AND ri.id > :cursor ORDER BY ri.id ASC")
    List<ReviewImage> findByStoreIdCursor(@Param("storeId") Long storeId, @Param("cursor") Long cursor, Pageable pageable);

    @Query("SELECT ri FROM ReviewImage ri WHERE ri.review.id IN :reviewIds")
    List<ReviewImage> findAllByReviewIdIn(@Param("reviewIds") List<Long> reviewIds);

    @Modifying
    @Query("DELETE FROM ReviewImage ri WHERE ri.review.id = :reviewId")
    void deleteByReviewId(@Param("reviewId") Long reviewId);
}
