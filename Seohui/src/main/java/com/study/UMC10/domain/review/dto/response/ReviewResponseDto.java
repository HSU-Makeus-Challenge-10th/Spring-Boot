package com.study.UMC10.domain.review.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class ReviewResponseDto {

    @Builder
    @Schema(description = "리뷰 생성")
    public record CreateReviewResultDto(
            @Schema(description = "생성된 리뷰 ID", example = "1")
            Long reviewId,

            @Schema(description = "리뷰가 작성된 가게 ID", example = "15")
            Long storeId,

            @Schema(description = "작성한 별점", example = "4.5")
            Double rate,

            @Schema(description = "작성한 리뷰 내용", example = "음식이 맛있습니다!")
            String content,

            @Schema(description = "첨부된 리뷰 이미지 URL 목록", example = "[\"https://s3.url.com/image1.jpg\"]")
            List<String> images
    ) {
    }

    @Builder
    @Schema(description = "내가 작성한 리뷰 상세 정보")
    public record MyReviewDto(
            @Schema(description = "리뷰 ID", example = "1")
            Long reviewId,

            @Schema(description = "가게 이름", example = "반이학생마라탕마라반")
            String storeName,

            @Schema(description = "유저 닉네임", example = "닉네임1234")
            String nickname,

            @Schema(description = "별점", example = "4.5")
            Double score,

            @Schema(description = "작성 날짜", example = "2022.05.14")
            LocalDate createdAt,

            @Schema(description = "사장님 답글 내용 (없으면 null)", example = "감사합니다.")
            String ownerComment,

            @Schema(description = "사장님 답글 작성 날짜 (없으면 null)", example = "2022.05.15")
            LocalDate ownerCommentCreatedAt
    ) {}

    @Builder
    @Schema(description = "커서 기반 페이징")
    public record CursorPagination<T>(
            @Schema(description = "데이터 리스트")
            List<T> data,

            @Schema(description = "다음 페이지 존재 여부", example = "true")
            Boolean hasNext,

            @Schema(description = "다음 페이지 조회를 위한 커서 값", example = "383:383")
            String nextCursor,

            @Schema(description = "요청한 데이터 수", example = "10")
            Integer pageSize
    ) {}
}