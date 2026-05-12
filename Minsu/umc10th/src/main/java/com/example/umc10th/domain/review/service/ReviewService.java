package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.entity.ReviewImage;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewImageRepository;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.exception.StoreException;
import com.example.umc10th.domain.store.exception.code.StoreErrorCode;
import com.example.umc10th.domain.store.repository.StoreRepository;
import com.example.umc10th.global.converter.PaginationConverter;
import com.example.umc10th.global.dto.CommonResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    private static final Long TEMP_MEMBER_ID = 1L;
    private static final String UPLOAD_DIR = "uploads/";

    public CommonResDTO.CursorPagination<ReviewResDTO.MyReviewCursorItem> getMyReviews(
            Long userId,
            String cursor,
            int limit,
            String query
    ) {
        validatePageSize(limit);
        memberRepository.findById(userId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(0, limit);
        String normalizedQuery = query == null ? "id" : query.toLowerCase();
        boolean firstPage = cursor == null || cursor.equals("-1") || cursor.equals("0");

        Slice<Review> slice;
        if (firstPage) {
            slice = switch (normalizedQuery) {
                case "id" -> reviewRepository.findByMember_IdOrderByIdDesc(userId, pageRequest);
                case "rating" -> reviewRepository.findByMember_IdOrderByRatingDescIdDesc(userId, pageRequest);
                default -> throw new ReviewException(ReviewErrorCode.QUERY_NOT_VALID);
            };
        } else {
            slice = switch (normalizedQuery) {
                case "id" -> reviewRepository.findByMember_IdAndIdLessThanOrderByIdDesc(
                        userId,
                        parseLongCursor(cursor),
                        pageRequest
                );
                case "rating" -> {
                    String[] cursorParts = cursor.split(":");
                    if (cursorParts.length != 2) {
                        throw new ReviewException(ReviewErrorCode.INVALID_CURSOR);
                    }
                    yield reviewRepository.findByRatingCursor(
                            userId,
                            parseDoubleCursor(cursorParts[0]),
                            parseLongCursor(cursorParts[1]),
                            pageRequest
                    );
                }
                default -> throw new ReviewException(ReviewErrorCode.QUERY_NOT_VALID);
            };
        }

        List<ReviewResDTO.MyReviewCursorItem> data = slice.getContent().stream()
                .map(ReviewConverter::toMyReviewCursorItem)
                .toList();
        String nextCursor = getNextCursor(slice.getContent(), normalizedQuery);

        return PaginationConverter.toCursorPagination(slice, data, nextCursor);
    }

    @Transactional
    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.REVIEW_NOT_FOUND));
        reviewRepository.delete(review);
    }

    @Transactional
    public ReviewResDTO.ReviewInfo createReview(Long storeId, ReviewReqDTO.CreateReview dto, List<MultipartFile> images) {
        Member member = memberRepository.findById(TEMP_MEMBER_ID)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Review review = Review.builder()
                .member(member)
                .store(store)
                .rating(dto.rating())
                .body(dto.body())
                .build();

        reviewRepository.save(review);
        saveImages(review, images);

        return ReviewConverter.toReviewInfo(review);
    }

    @Transactional
    public ReviewResDTO.ReviewInfo updateReview(Long reviewId, ReviewReqDTO.Update dto, List<MultipartFile> images) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.REVIEW_NOT_FOUND));

        if (dto != null) {
            review.update(dto.rating(), dto.body());
        }

        if (images != null && !images.isEmpty()) {
            reviewImageRepository.deleteByReviewId(reviewId);
            review.getImages().clear();
            saveImages(review, images);
        }

        return ReviewConverter.toReviewInfo(review);
    }

    public ReviewResDTO.StoreReviewPageResult getStoreReviews(Long storeId, Long cursor, int limit) {
        storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));
        List<Review> list = reviewRepository.findByStoreIdWithDetailsCursor(storeId, cursor, PageRequest.of(0, limit));
        List<Long> reviewIds = list.stream()
                .map(Review::getId)
                .toList();
        Map<Long, List<String>> imageUrlsByReviewId = reviewIds.isEmpty()
                ? Map.of()
                : reviewImageRepository.findAllByReviewIdIn(reviewIds).stream()
                .collect(Collectors.groupingBy(
                        reviewImage -> reviewImage.getReview().getId(),
                        Collectors.mapping(ReviewImage::getImgUrl, Collectors.toList())
                ));
        return ReviewConverter.toStoreReviewPageResult(list, imageUrlsByReviewId, limit);
    }

    public ReviewResDTO.ReviewImagePageResult getStoreReviewImages(Long storeId, Long cursor, int limit) {
        storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));
        List<ReviewImage> list = reviewImageRepository.findByStoreIdCursor(storeId, cursor, PageRequest.of(0, limit));
        return ReviewConverter.toReviewImagePageResult(list, limit);
    }

    private String saveFile(MultipartFile file) {
        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(filename);
            file.transferTo(filePath.toFile());
            return "/" + UPLOAD_DIR + filename;
        } catch (IOException e) {
            return "/uploads/placeholder.jpg";
        }
    }

    private void saveImages(Review review, List<MultipartFile> images) {
        if (images == null || images.isEmpty()) {
            return;
        }

        List<ReviewImage> reviewImages = new ArrayList<>();
        for (MultipartFile file : images) {
            String imgUrl = saveFile(file);
            ReviewImage reviewImage = ReviewImage.builder()
                    .review(review)
                    .imgUrl(imgUrl)
                    .build();
            reviewImages.add(reviewImage);
            review.getImages().add(reviewImage);
        }
        reviewImageRepository.saveAll(reviewImages);
    }

    private String getNextCursor(List<Review> reviews, String query) {
        if (reviews.isEmpty()) {
            return null;
        }

        Review last = reviews.get(reviews.size() - 1);
        if (query.equals("rating")) {
            return last.getRating() + ":" + last.getId();
        }
        return String.valueOf(last.getId());
    }

    private Long parseLongCursor(String cursor) {
        try {
            return Long.parseLong(cursor);
        } catch (NumberFormatException e) {
            throw new ReviewException(ReviewErrorCode.INVALID_CURSOR);
        }
    }

    private Double parseDoubleCursor(String cursor) {
        try {
            return Double.parseDouble(cursor);
        } catch (NumberFormatException e) {
            throw new ReviewException(ReviewErrorCode.INVALID_CURSOR);
        }
    }

    private void validatePageSize(int limit) {
        if (limit < 1) {
            throw new ReviewException(ReviewErrorCode.INVALID_PAGE_SIZE);
        }
    }
}
