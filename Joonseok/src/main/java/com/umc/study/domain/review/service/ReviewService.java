package com.umc.study.domain.review.service;

import com.umc.study.domain.mission.entity.Restaurant;
import com.umc.study.domain.mission.repository.RestaurantRepository;
import com.umc.study.domain.review.entity.Review;
import com.umc.study.domain.review.repository.ReviewRepository;
import com.umc.study.domain.review.web.dto.CreateReviewReq;
import com.umc.study.domain.user.entity.User;
import com.umc.study.domain.user.exception.UserNotFoundException;
import com.umc.study.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    // create Review
    @Transactional
    public Void createReview(Long userId, CreateReviewReq request) {

        // found User
        User found = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId())
                .orElseThrow(IllegalStateException::new);


        // build Review
        Review created = Review.builder()
                .user(found)
                .score(request.getScore())
                .content(request.getContent())
                .restaurant(restaurant)
                .build();

        // create Review
        reviewRepository.save(created);

        return null;
    }

    // find all my review by cursor paging
    public CursorRes<ReviewDetail, Long> getAllMyReviews() {
        // 1. query

        // return
    }
}
