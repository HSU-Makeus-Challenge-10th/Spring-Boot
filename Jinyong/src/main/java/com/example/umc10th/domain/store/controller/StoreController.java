package com.example.umc10th.domain.store.controller;

import com.example.umc10th.domain.store.code.StoreSuccessCode;
import com.example.umc10th.domain.store.dto.StoreResDTO;
import com.example.umc10th.domain.store.service.StoreService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/v1/stores")
    public ApiResponse<List<StoreResDTO.StoreInfo>> getStoreList() {
        BaseSuccessCode code = StoreSuccessCode.OK;
        return ApiResponse.onSuccess(code, storeService.getStoreList());
    }
}