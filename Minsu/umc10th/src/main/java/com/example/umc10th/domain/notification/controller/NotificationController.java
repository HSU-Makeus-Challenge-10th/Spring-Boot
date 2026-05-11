package com.example.umc10th.domain.notification.controller;

import com.example.umc10th.domain.notification.dto.NotificationReqDTO;
import com.example.umc10th.domain.notification.dto.NotificationResDTO;
import com.example.umc10th.domain.notification.service.NotificationService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/notifications")
@Tag(name = "Notification", description = "알림 API")
public class NotificationController {

    private static final Long TEMP_MEMBER_ID = 1L;
    private final NotificationService notificationService;

    @GetMapping("")
    @Operation(summary = "알림 설정 조회")
    public ApiResponse<NotificationResDTO.Settings> getSettings() {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, notificationService.getSettings(TEMP_MEMBER_ID));
    }

    @PutMapping("")
    @Operation(summary = "알림 설정 수정")
    public ApiResponse<Void> updateSettings(@RequestBody NotificationReqDTO.Update request) {
        notificationService.updateSettings(TEMP_MEMBER_ID, request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }
}
