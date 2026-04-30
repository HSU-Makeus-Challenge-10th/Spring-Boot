package com.example.umc10thweek4.domain.ask.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class AskReqDTO {

    @NotBlank
    private String askTitle;

    @NotBlank
    private String askDetail;

    @NotBlank
    private String askType;

    private List<String> imageUrls;
}
