package com.example.umc10th.global.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record OffsetPageRes<T>(
        List<T> list,
        Integer listSize,
        Integer currentPage,
        Integer totalPage,
        Long totalCount,
        Boolean isFirst,
        Boolean isLast
) {}
