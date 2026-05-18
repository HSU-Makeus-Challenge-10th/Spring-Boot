package com.example.umc10th.global.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record CursorPageRes<T>(
        List<T> list,
        Integer listSize,
        Boolean hasNext,
        Long nextCursor,
        Double nextRatingCursor,
        Boolean isFirst,
        Boolean isLast
) {}
