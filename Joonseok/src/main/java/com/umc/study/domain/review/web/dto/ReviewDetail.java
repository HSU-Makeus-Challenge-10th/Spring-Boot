package com.umc.study.domain.review.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
public class ReviewDetail {

    private final Long reviewId;
    private final Long restaurantId;

    private final String username;

    private final LocalDateTime createdAt;

    private final Double score;

    private final String content;

}
