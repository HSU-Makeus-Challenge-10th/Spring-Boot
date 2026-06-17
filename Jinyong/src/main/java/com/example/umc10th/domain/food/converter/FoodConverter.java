package com.example.umc10th.domain.food.converter;

import com.example.umc10th.domain.food.dto.FoodResDTO;
import com.example.umc10th.domain.food.entity.Food;

public class FoodConverter {

    public static FoodResDTO.FoodInfo toFoodInfo(Food food) {
        return FoodResDTO.FoodInfo.builder()
                .id(food.getId())
                .name(food.getName().name())
                .build();
    }
}
