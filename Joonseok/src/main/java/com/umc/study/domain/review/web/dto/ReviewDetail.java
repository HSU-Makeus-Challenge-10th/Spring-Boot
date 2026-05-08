package com.umc.study.domain.review.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
public class ReviewDetail {

    private final Long reviewId;
    private final Long restaurantId;

    private final String username;

    private final LocalDate createdAt;

    private final String score;

    private final String Content;

}
