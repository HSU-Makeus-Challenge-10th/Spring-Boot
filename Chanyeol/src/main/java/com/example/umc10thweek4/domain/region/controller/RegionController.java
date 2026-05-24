package com.example.umc10thweek4.domain.region.controller;

import com.example.umc10thweek4.domain.region.dto.RegionResDTO;
import com.example.umc10thweek4.domain.region.exception.code.RegionSuccessCode;
import com.example.umc10thweek4.domain.region.service.RegionService;
import com.example.umc10thweek4.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RegionController {

    private final RegionService regionService;

    @GetMapping("/v1/regions")
    public ResponseEntity<ApiResponse<RegionResDTO.RegionList>> getRegions() {
        return ApiResponse.onSuccessResponse(RegionSuccessCode.OK, regionService.getAllRegions());
    }

    @GetMapping("/v1/regions/{regionId}")
    public ResponseEntity<ApiResponse<RegionResDTO.RegionInfo>> getRegion(@PathVariable Long regionId) {
        return ApiResponse.onSuccessResponse(RegionSuccessCode.OK, regionService.getRegion(regionId));
    }
}
