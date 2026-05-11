package com.example.umc10th.domain.store.controller;

import com.example.umc10th.domain.store.dto.StoreResDTO;
import com.example.umc10th.domain.store.service.StoreService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.apiPayload.code.MemberSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/{storeId}")
    public ApiResponse<StoreResDTO.StoreInfo> getStoreInfo(@PathVariable Long storeId) {
        return ApiResponse.onSuccess(MemberSuccessCode.OK, storeService.getStoreInfo(storeId));
    }
}
