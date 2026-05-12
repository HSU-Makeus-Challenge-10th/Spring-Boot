package com.example.umc10th.global.converter;

import com.example.umc10th.global.dto.CommonResDTO;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PaginationConverterTest {

    @Test
    void offsetPaginationUsesPageMetadata() {
        PageRequest pageRequest = PageRequest.of(1, 2);
        PageImpl<String> page = new PageImpl<>(List.of("a", "b"), pageRequest, 5);

        CommonResDTO.OffsetPagination<String> result =
                PaginationConverter.toOffsetPagination(page, page.getContent());

        assertEquals(List.of("a", "b"), result.data());
        assertEquals(1, result.pageNumber());
        assertEquals(2, result.pageSize());
        assertEquals(5, result.totalElements());
        assertEquals(3, result.totalPages());
        assertFalse(result.isFirst());
        assertFalse(result.isLast());
    }

    @Test
    void cursorPaginationUsesSliceMetadata() {
        PageRequest pageRequest = PageRequest.of(0, 2);
        SliceImpl<String> slice = new SliceImpl<>(List.of("a", "b"), pageRequest, true);

        CommonResDTO.CursorPagination<String> result =
                PaginationConverter.toCursorPagination(slice, slice.getContent(), "2");

        assertEquals(List.of("a", "b"), result.data());
        assertTrue(result.hasNext());
        assertEquals("2", result.nextCursor());
        assertEquals(2, result.pageSize());
    }
}
