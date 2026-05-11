package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByMember_Id(Long memberId, Pageable pageable);

    List<Review> member(Member member);

    // id 순 페이징
    Slice<Review> findReviewsByMember_IdAndIdLessThanOrderByIdDesc(Long memberId, Long idCursor, Pageable pageable);
    Slice<Review> findReviewsByMember_IdOrderByIdDesc(Long memberId, Pageable pageable);

    // 별점 순 페이징
    Slice<Review> findReviewsByMember_IdOrderByScoreDescIdDesc(Long memberId, Pageable pageable);

    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId " +
            "AND (r.score < :scoreCursor OR (r.score = :scoreCursor AND r.id < :idCursor)) " +
            "ORDER BY r.score DESC, r.id DESC")
    Slice<Review> findReviewsByScoreCursor(
            @Param("memberId") Long memberId,
            @Param("scoreCursor") int scoreCursor,
            Pageable pageable
    );
}
