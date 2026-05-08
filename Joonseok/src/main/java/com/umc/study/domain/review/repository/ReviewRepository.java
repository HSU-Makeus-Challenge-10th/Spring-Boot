package com.umc.study.domain.review.repository;

import com.umc.study.domain.review.entity.Review;
import com.umc.study.domain.review.web.dto.ReviewDetail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("""
select new com.umc.study.domain.review.web.dto.ReviewDetail(
    rv.id,
    rv.restaurant.id,
    rv.user.name,
    rv.createdAt,
    rv.score,
    rv.content
) from Review rv
where rv.user.id = :userId
    and rv.id < :cursor
order by rv.score desc
""")
    List<ReviewDetail> findReviewDetailByUserIdOrderByScore(
            @Param("userId") Long userId,
            @Param("cursor") Long cursor,
            Pageable pageable);

    @Query("""
    select new com.umc.study.domain.review.web.dto.ReviewDetail(
        rv.id,
        rv.restaurant.id,
        rv.user.name,
        rv.createdAt,
        rv.score,
        rv.content
    ) from Review rv
    where rv.user.id = :userId
        and rv.id < :cursor
    order by rv.id desc
    """)
    List<ReviewDetail> findReviewDetailByUserId(
            @Param("userId") Long userId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );

}
