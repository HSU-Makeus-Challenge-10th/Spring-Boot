package com.example.umc10th.domain.store.controller;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.store.dto.StoreResDTO;
import com.example.umc10th.domain.store.exception.code.StoreSuccessCode;
import com.example.umc10th.domain.store.service.StoreService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
@Tag(name = "Store", description = "가게 API")
public class StoreController {

    private final StoreService storeService;

    @GetMapping("")
    @Operation(summary = "가게 전체 조회 (커서 페이징)")
    public ApiResponse<StoreResDTO.StoreList> getStores(
            @RequestParam Long townId,
            @RequestParam Long foodTypeId,
            @RequestParam(defaultValue = "0") Long cursor,
            @RequestParam(defaultValue = "10") int limit) {
        return ApiResponse.onSuccess(StoreSuccessCode.OK, storeService.getStores(townId, foodTypeId, cursor, limit));
    }

    @GetMapping("/{storeId}")
    @Operation(summary = "가게 단일 조회")
    public ApiResponse<StoreResDTO.StoreInfo> getStore(@PathVariable Long storeId) {
        return ApiResponse.onSuccess(StoreSuccessCode.OK, storeService.getStoreById(storeId));
    }

    @GetMapping("/{storeId}/missions")
    @Operation(summary = "가게 미션 조회 (커서 페이징)")
    public ApiResponse<MissionResDTO.StoreMissionPageResult> getStoreMissions(
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "0") Long cursor,
            @RequestParam(defaultValue = "10") int limit) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, storeService.getStoreMissions(storeId, cursor, limit));
    }
}
