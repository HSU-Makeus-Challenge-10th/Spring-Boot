package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    boolean existsByMemberMission(MemberMission memberMission);

    @Query("SELECT r FROM Review r JOIN FETCH r.memberMission mm JOIN FETCH mm.mission m JOIN FETCH m.store s " +
            "WHERE r.member.id = :memberId ORDER BY r.id DESC")
    Slice<Review> findByMemberIdOrderByIdDesc(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT r FROM Review r JOIN FETCH r.memberMission mm JOIN FETCH mm.mission m JOIN FETCH m.store s " +
            "WHERE r.member.id = :memberId AND r.id < :cursor ORDER BY r.id DESC")
    Slice<Review> findByMemberIdAndIdCursorOrderByIdDesc(@Param("memberId") Long memberId,
                                                         @Param("cursor") Long cursor,
                                                         Pageable pageable);

    @Query("SELECT r FROM Review r JOIN FETCH r.memberMission mm JOIN FETCH mm.mission m JOIN FETCH m.store s " +
            "WHERE r.member.id = :memberId ORDER BY r.rating DESC, r.id DESC")
    Slice<Review> findByMemberIdOrderByRatingDescIdDesc(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT r FROM Review r JOIN FETCH r.memberMission mm JOIN FETCH mm.mission m JOIN FETCH m.store s " +
            "WHERE r.member.id = :memberId AND r.rating < :ratingCursor " +
            "ORDER BY r.rating DESC")
    Slice<Review> findByMemberIdWithRatingCursor(@Param("memberId") Long memberId,
                                                  @Param("ratingCursor") Double ratingCursor,
                                                  Pageable pageable);
}
