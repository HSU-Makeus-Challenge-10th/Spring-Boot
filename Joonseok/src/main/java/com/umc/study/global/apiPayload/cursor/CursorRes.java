package com.umc.study.global.apiPayload.cursor;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({
        "nextCursor",
        "prevCursor",
        "hasNext",
        "pageSize",
        "data"
})
public class CursorRes<D, C> {

    private final C nextCursor;
    private final C prevCursor;
    private final Boolean hasNext;
    private final Integer pageSize;

    private final D data;

    public static <D, C> CursorRes<D, C> last(
            C prevCursor,
            int pageSize,
            D data
    ) {
        return new CursorRes<>(null, prevCursor, false, pageSize, data);
    }

    public static <D, C> CursorRes<D, C> of(
            C nextCursor,
            C prevCursor,
            int pageSize,
            D data
    ) {
        return new CursorRes<>(
                nextCursor,
                prevCursor,
                true,
                pageSize,
                data
        );
    }
}
