package com.example.umc10thweek4.domain.ask.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class AskResDTO {

    @Builder
    public record Create(
            Long askId,
            String askTitle,
            String askType,
            LocalDateTime createdAt         // ERD: ask_time → createdAt
    ) {}

    @Builder
    public record GetList(
            Long askId,
            String askTitle,
            String askType,
            String status,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record GetDetail(
            Long askId,
            String askTitle,
            String askDetail,
            String askType,
            String status,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            List<String> imageUrls,
            AskReply reply
    ) {
        public record AskReply(
                Long replyId,
                String content,
                LocalDateTime replyCreatedAt
        ) {}
    }
}