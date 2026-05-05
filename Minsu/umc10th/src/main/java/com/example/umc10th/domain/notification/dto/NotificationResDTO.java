package com.example.umc10th.domain.notification.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

public class NotificationResDTO {

    @Builder
    public record Settings(
            @JsonProperty("event_enabled")
            @Schema(description = "이벤트 알림 허용 여부", example = "true")
            Boolean eventEnabled,
            @JsonProperty("review_reply_enabled")
            @Schema(description = "리뷰 답글 알림 허용 여부", example = "true")
            Boolean reviewReplyEnabled,
            @JsonProperty("inquiry_reply_enabled")
            @Schema(description = "문의 답변 알림 허용 여부", example = "false")
            Boolean inquiryReplyEnabled
    ) {}
}
