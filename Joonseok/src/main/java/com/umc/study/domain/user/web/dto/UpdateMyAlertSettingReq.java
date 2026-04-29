package com.umc.study.domain.user.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateMyAlertSettingReq {

    @NotNull(message = "newEvent 필드는 비어있을 수 없습니다.")
    private final Boolean newEvent;

    @NotNull(message = "reviewComment 필드는 비어있을 수 없습니다.")
    private final Boolean reviewComment;

    @NotNull(message = "askedComment 필드는 비어있을 수 없습니다.")
    private final Boolean askedComment;
}
