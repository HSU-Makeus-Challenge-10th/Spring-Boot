package com.umc.study.domain.user.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateUserPrefFoodReq {

    @NotNull(message = "prefFood 필드는 비어있을 수 없습니다.")
    private final Object prefFood;
}
