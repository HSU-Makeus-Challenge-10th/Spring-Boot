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
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public ReviewResDTO.MyReviewPageResult getMyReviews(Long userId, Long cursor, int limit) {
        Long targetId = userId != null ? userId : TEMP_MEMBER_ID;
        List<Review> list = reviewRepository.findByMemberIdCursor(targetId, cursor, PageRequest.of(0, limit));
        return ReviewConverter.toMyReviewPageResult(list, limit);
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

        if (images != null && !images.isEmpty()) {
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

        return ReviewConverter.toReviewInfo(review);
    }

    public ReviewResDTO.StoreReviewPageResult getStoreReviews(Long storeId, Long cursor, int limit) {
        storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));
        List<Review> list = reviewRepository.findByStoreIdCursor(storeId, cursor, PageRequest.of(0, limit));
        return ReviewConverter.toStoreReviewPageResult(list, limit);
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
}
