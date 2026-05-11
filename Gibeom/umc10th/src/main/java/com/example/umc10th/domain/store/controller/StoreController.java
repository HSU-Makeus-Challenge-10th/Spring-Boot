package com.example.umc10th.domain.store.controller;

import com.example.umc10th.domain.store.dto.StoreResDTO;
import com.example.umc10th.domain.store.exception.code.StoreSuccessCode;
import com.example.umc10th.domain.store.service.StoreService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/v1/stores")  // [수정] 경로 앞에 / 추가
    public ApiResponse<List<StoreResDTO.StoreDTO>> getStoreList(
            @RequestParam Long regionId  // [수정] Region 엔티티 → Long
    ){
        BaseSuccessCode code = StoreSuccessCode.OK;
        return ApiResponse.onSuccess(code, storeService.getStoreList(regionId));
    }
}
