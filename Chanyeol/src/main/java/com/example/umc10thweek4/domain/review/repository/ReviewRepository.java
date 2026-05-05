package com.example.umc10thweek4.domain.review.repository;

import com.example.umc10thweek4.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findByIdAndDeletedAtIsNull(Long id);

    // 특정 회원이 작성한 리뷰 목록
    List<Review> findByMemberIdAndDeletedAtIsNull(Long memberId);

    // 특정 UserMission에 대한 리뷰 조회
    Optional<Review> findByUserMissionIdAndDeletedAtIsNull(Long userMissionId);

    // 특정 가게의 리뷰 목록
    @Query("SELECT r FROM Review r WHERE r.userMission.mission.store.id = :storeId AND r.deletedAt IS NULL")
    List<Review> findByStoreId(@Param("storeId") Long storeId);
}