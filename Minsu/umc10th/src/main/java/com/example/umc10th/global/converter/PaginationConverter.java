package com.example.umc10th.global.converter;

import com.example.umc10th.global.dto.CommonResDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

import java.util.List;

public class PaginationConverter {

    public static <T> CommonResDTO.OffsetPagination<T> toOffsetPagination(Page<?> page, List<T> data) {
        return CommonResDTO.OffsetPagination.<T>builder()
                .data(data)
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }

    public static <T> CommonResDTO.CursorPagination<T> toCursorPagination(
            Slice<?> slice,
            List<T> data,
            String nextCursor
    ) {
        return CommonResDTO.CursorPagination.<T>builder()
                .data(data)
                .hasNext(slice.hasNext())
                .nextCursor(nextCursor)
                .pageSize(slice.getSize())
                .build();
    }
}
