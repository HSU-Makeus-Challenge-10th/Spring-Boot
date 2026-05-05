package com.study.UMC10.domain.review.repository;

import com.study.UMC10.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}