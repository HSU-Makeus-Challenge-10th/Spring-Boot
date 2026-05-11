package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.entity.ReviewImage;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ReviewConverter {

    public static ReviewResDTO.ReviewInfo toReviewInfo(Review review) {
        List<String> imgUrls = review.getImages().stream()
                .map(ReviewImage::getImgUrl).toList();
        return ReviewResDTO.ReviewInfo.builder()
                .reviewId(String.valueOf(review.getId()))
                .storeId(String.valueOf(review.getStore().getId()))
                .rating(review.getRating())
                .body(review.getBody())
                .imgUrls(imgUrls)
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResDTO.MyReviewItem toMyReviewItem(Review review) {
        List<String> imgUrls = review.getImages().stream()
                .map(ReviewImage::getImgUrl).toList();
        return ReviewResDTO.MyReviewItem.builder()
                .reviewId(String.valueOf(review.getId()))
                .storeName(review.getStore().getName())
                .rating(review.getRating())
                .body(review.getBody())
                .imgUrls(imgUrls)
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResDTO.MyReviewPageResult toMyReviewPageResult(List<Review> list, int limit) {
        boolean hasNext = list.size() == limit;
        String nextCursor = list.isEmpty() ? null : String.valueOf(list.get(list.size() - 1).getId());
        return ReviewResDTO.MyReviewPageResult.builder()
                .reviews(list.stream().map(ReviewConverter::toMyReviewItem).toList())
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .build();
    }

    public static ReviewResDTO.StoreReviewItem toStoreReviewItem(Review review) {
        List<String> imgUrls = review.getImages().stream()
                .map(ReviewImage::getImgUrl).toList();
        return toStoreReviewItem(review, imgUrls);
    }

    public static ReviewResDTO.StoreReviewItem toStoreReviewItem(Review review, List<String> imgUrls) {
        ReviewResDTO.OwnerReply ownerReply = review.getComment() != null
                ? ReviewResDTO.OwnerReply.builder().body(review.getComment().getBody()).build()
                : null;
        return ReviewResDTO.StoreReviewItem.builder()
                .reviewId(String.valueOf(review.getId()))
                .reviewerNickname(review.getMember().getName())
                .rating(review.getRating())
                .body(review.getBody())
                .imgUrls(imgUrls)
                .createdAt(review.getCreatedAt())
                .ownerReply(ownerReply)
                .build();
    }

    public static ReviewResDTO.StoreReviewPageResult toStoreReviewPageResult(List<Review> list, int limit) {
        boolean hasNext = list.size() == limit;
        String nextCursor = list.isEmpty() ? null : String.valueOf(list.get(list.size() - 1).getId());
        return ReviewResDTO.StoreReviewPageResult.builder()
                .reviews(list.stream().map(ReviewConverter::toStoreReviewItem).toList())
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .build();
    }

    public static ReviewResDTO.StoreReviewPageResult toStoreReviewPageResult(
            List<Review> list,
            Map<Long, List<String>> imageUrlsByReviewId,
            int limit
    ) {
        boolean hasNext = list.size() == limit;
        String nextCursor = list.isEmpty() ? null : String.valueOf(list.get(list.size() - 1).getId());
        return ReviewResDTO.StoreReviewPageResult.builder()
                .reviews(list.stream()
                        .map(review -> toStoreReviewItem(
                                review,
                                imageUrlsByReviewId.getOrDefault(review.getId(), Collections.emptyList())
                        ))
                        .toList())
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .build();
    }

    public static ReviewResDTO.ReviewImageItem toReviewImageItem(ReviewImage ri) {
        return ReviewResDTO.ReviewImageItem.builder()
                .reviewImgId(String.valueOf(ri.getId()))
                .imgUrl(ri.getImgUrl())
                .reviewId(String.valueOf(ri.getReview().getId()))
                .createdAt(ri.getCreatedAt())
                .build();
    }

    public static ReviewResDTO.ReviewImagePageResult toReviewImagePageResult(List<ReviewImage> list, int limit) {
        boolean hasNext = list.size() == limit;
        String nextCursor = list.isEmpty() ? null : String.valueOf(list.get(list.size() - 1).getId());
        return ReviewResDTO.ReviewImagePageResult.builder()
                .images(list.stream().map(ReviewConverter::toReviewImageItem).toList())
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .build();
    }
}
