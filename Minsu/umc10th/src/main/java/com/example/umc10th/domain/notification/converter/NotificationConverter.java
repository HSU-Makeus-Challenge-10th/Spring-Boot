package com.example.umc10th.domain.notification.converter;

import com.example.umc10th.domain.notification.dto.NotificationResDTO;
import com.example.umc10th.domain.notification.entity.NotificationSettings;

public class NotificationConverter {

    public static NotificationResDTO.Settings toSettings(NotificationSettings settings) {
        return NotificationResDTO.Settings.builder()
                .eventEnabled(settings.getEventEnabled())
                .reviewReplyEnabled(settings.getReviewReplyEnabled())
                .inquiryReplyEnabled(settings.getInquiryReplyEnabled())
                .build();
    }

    public static NotificationResDTO.Settings toDefaultSettings() {
        return NotificationResDTO.Settings.builder()
                .eventEnabled(true)
                .reviewReplyEnabled(true)
                .inquiryReplyEnabled(true)
                .build();
    }
}
