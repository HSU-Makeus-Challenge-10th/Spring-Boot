package com.example.umc10th.domain.food.controller;

import com.example.umc10th.domain.food.code.FoodSuccessCode;
import com.example.umc10th.domain.food.dto.FoodResDTO;
import com.example.umc10th.domain.food.service.FoodService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FoodController {

    private final FoodService foodService;

    @GetMapping("v1/foods")
    public ApiResponse<List<FoodResDTO.FoodInfo>> getFoodList() {
        BaseSuccessCode code = FoodSuccessCode.OK;
        return ApiResponse.onSuccess(code, foodService.getFoodList());
    }
}
