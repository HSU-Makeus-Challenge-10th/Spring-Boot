package com.example.umc10th.domain.review.dto;

public class ReviewReqDTO {

    // 리뷰 쓰는 시점
    public record PostReviewDTO(
            Integer rating, // 사용자가 입력한 별점
            String content, // 사용자가 작성한 리뷰
            Long memberId, // 리뷰를 쓰는 사람의 ID
            Long storeId // 어느 가게에 쓰는지에 대한 ID
    ){}
}
