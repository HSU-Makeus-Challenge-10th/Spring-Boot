package com.example.umc10th.domain.food.service;

import com.example.umc10th.domain.food.converter.FoodConverter;
import com.example.umc10th.domain.food.dto.FoodResDTO;
import com.example.umc10th.domain.food.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;

    public List<FoodResDTO.FoodInfo> getFoodList() {

        return foodRepository.findAll().stream()
                .map(FoodConverter::toFoodInfo)
                .toList();
    }

}
