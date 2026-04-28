package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    public ReviewResDTO.CreateReviewRes createReview(Long missionId, ReviewReqDTO.CreateReviewReq request, List<MultipartFile> reviewImages) {
        // TODO: 리뷰 작성 로직
        return null;
    }
}
