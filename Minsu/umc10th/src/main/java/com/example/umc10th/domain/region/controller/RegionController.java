package com.example.umc10th.domain.region.controller;

import com.example.umc10th.domain.region.entity.Town;
import com.example.umc10th.domain.region.service.RegionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/regions")
@Tag(name = "Region", description = "지역 API")
public class RegionController {

    private final RegionService regionService;

    @GetMapping("/towns")
    @Operation(summary = "전체 동네 목록 조회")
    public ApiResponse<List<Town>> getTowns() {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, regionService.getAllTowns());
    }
}
