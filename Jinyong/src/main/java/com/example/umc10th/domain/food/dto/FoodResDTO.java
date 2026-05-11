package com.example.umc10th.domain.food.dto;

import lombok.Builder;

public class FoodResDTO {

    @Builder
    public record FoodInfo(
            Long id,
            String name
    ) {}
}
