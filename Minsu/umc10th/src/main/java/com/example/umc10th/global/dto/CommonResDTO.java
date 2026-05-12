package com.example.umc10th.global.dto;

import lombok.Builder;

import java.util.List;

public class CommonResDTO {

    @Builder
    public record OffsetPagination<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize,
            Long totalElements,
            Integer totalPages,
            Boolean isFirst,
            Boolean isLast
    ) {
    }

    @Builder
    public record CursorPagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {
    }
}
