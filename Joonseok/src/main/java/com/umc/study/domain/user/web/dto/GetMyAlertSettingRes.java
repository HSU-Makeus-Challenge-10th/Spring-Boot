package com.umc.study.domain.user.web.dto;

public record GetMyAlertSettingRes(
        Boolean newEvent,
        Boolean reviewComment,
        Boolean askedComment
) {
}
