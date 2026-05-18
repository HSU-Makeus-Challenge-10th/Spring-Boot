package com.example.umc10thweek4.domain.ask.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public class AskReqDTO {

    public record Create(

            @NotBlank(message = "문의 제목을 입력해주세요")
            String askTitle,

            @NotBlank(message = "문의 내용을 입력해주세요")
            String askDetail,

            @NotBlank(message = "문의 유형을 선택해주세요")
            String askType,

            @Size(max = 5, message = "이미지는 최대 5개까지만 등록할 수 있습니다")
            List<String> imageUrls
    ) {}
}