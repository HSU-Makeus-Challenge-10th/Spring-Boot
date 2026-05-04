package com.example.umc10thweek4.domain.ask.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class AskReqDTO {

    public record Create(
            @NotBlank String askTitle,

            @NotBlank String askDetail,

            @NotBlank String askType,

            List<String> imageUrls
    ) {}
}
