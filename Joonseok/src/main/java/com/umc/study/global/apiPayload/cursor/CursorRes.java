package com.umc.study.global.apiPayload.cursor;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.function.Function;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({
        "nextCursor",
        "hasNext",
        "pageSize",
        "data"
})
public class CursorRes<D, C> {

    private final C nextCursor;
    private final Boolean hasNext;
    private final Integer pageSize;

    private final List<D> data;

    public static <D, C> CursorRes<D, C> of(
            C nextCursor,
            int pageSize,
            List<D> data
    ) {

        boolean hasNext = data.size() > pageSize;

        data = hasNext ? data.subList(0, pageSize) : data;

        return new CursorRes<>(
                hasNext ? nextCursor : null,
                hasNext,
                pageSize,
                data
        );
    }

}
