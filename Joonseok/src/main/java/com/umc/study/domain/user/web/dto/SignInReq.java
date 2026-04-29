package com.umc.study.domain.user.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class SignInReq {

    @NotNull(message = "allowLocation 필드는 비어있을 수 없습니다.")
    private final Boolean allowLocation;

    @NotNull(message = "allowAd 필드는 비어있을 수 없습니다.")
    private final Boolean allowAd;

    @NotBlank(message = "name 필드는 비어있을 수 없습니다.")
    private final String name;

    @NotBlank(message = "genderType 필드는 비어있을 수 없습니다.")
    private final String genderType;

    @NotNull(message = "birth 필드는 비어있을 수 없습니다.")
    private final LocalDate birth;

    @NotBlank(message = "address1 필드는 비어있을 수 없습니다.")
    private final String address1;

    @NotBlank(message = "address2 필드는 비어있을 수 없습니다.")
    private final String address2;
}
