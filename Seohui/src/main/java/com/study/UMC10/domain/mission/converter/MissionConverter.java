package com.study.UMC10.domain.mission.converter;

import com.study.UMC10.domain.mission.dto.response.MissionResponseDto;

import java.util.List;

public class MissionConverter {
    public static <T> MissionResponseDto.Pagination<T> toPagination(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){
        return MissionResponseDto.Pagination.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }
}
