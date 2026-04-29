package com.example.umc10th.domain.home.controller;

import com.example.umc10th.domain.store.dto.StoreResDTO;
import com.example.umc10th.domain.store.service.StoreService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Home", description = "홈화면 API")
public class HomeController {

    private final StoreService storeService;

    @GetMapping("/home")
    @Operation(summary = "홈화면 (지역별 미션 정보)")
    public ApiResponse<StoreResDTO.HomeInfo> home(
            @RequestParam(value = "town_id", required = false) Long townId) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, storeService.getHomeInfo(townId));
    }
}
