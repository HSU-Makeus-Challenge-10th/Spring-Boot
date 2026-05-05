package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 특정 가게의 리뷰 목록 조회
    @Query("SELECT r FROM Review r WHERE r.store.id = :storeId")
    Page<Review> findAllByStoreId(Long storeId, Pageable pageable);
}
