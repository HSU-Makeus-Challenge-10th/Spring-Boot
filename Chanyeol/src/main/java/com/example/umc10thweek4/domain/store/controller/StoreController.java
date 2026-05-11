package com.example.umc10thweek4.domain.store.controller;

import com.example.umc10thweek4.domain.store.dto.StoreResDTO;
import com.example.umc10thweek4.domain.store.exception.code.StoreSuccessCode;
import com.example.umc10thweek4.domain.store.service.StoreService;
import com.example.umc10thweek4.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/v1/regions/{regionId}/stores")
    public ApiResponse<StoreResDTO.StoreList> getStoresByRegion(
            @PathVariable Long regionId,
            @RequestParam(required = false) String category) {

        return ApiResponse.onSuccess(StoreSuccessCode.OK,
                storeService.getStoresByRegion(regionId, category));
    }

    @GetMapping("/v1/stores/{storeId}")
    public ApiResponse<StoreResDTO.StoreDetail> getStoreDetail(@PathVariable Long storeId) {
        return ApiResponse.onSuccess(StoreSuccessCode.OK,
                storeService.getStoreDetail(storeId));
    }
}