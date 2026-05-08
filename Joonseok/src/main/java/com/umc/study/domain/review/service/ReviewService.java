package com.umc.study.domain.review.service;

import com.umc.study.domain.mission.entity.Restaurant;
import com.umc.study.domain.mission.repository.RestaurantRepository;
import com.umc.study.domain.review.entity.Review;
import com.umc.study.domain.review.exception.IllegalReviewTypeException;
import com.umc.study.domain.review.repository.ReviewRepository;
import com.umc.study.domain.review.web.dto.CreateReviewReq;
import com.umc.study.domain.review.web.dto.ReviewDetail;
import com.umc.study.domain.user.entity.User;
import com.umc.study.domain.user.exception.UserNotFoundException;
import com.umc.study.domain.user.repository.UserRepository;
import com.umc.study.global.apiPayload.cursor.CursorRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

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
    public CursorRes<ReviewDetail, Long> getAllMyReviews(Long userId, String[] cursors, String query, int pageSize) {

        PageRequest pageRequest = PageRequest.of(0, pageSize);
        long idCursor;
        List<ReviewDetail> found;
        switch(query.toLowerCase()) {
            // 1. set Cursor
            case "id":
                idCursor = Long.parseLong(cursors[1]);

                found = reviewRepository.findReviewDetailByUserId(
                        userId,
                        idCursor,
                        pageRequest
                );
                break;
            case "score":
                idCursor = Long.parseLong(cursors[1]);

                found = reviewRepository.findReviewDetailByUserIdOrderByScore(
                        userId,
                        idCursor,
                        pageRequest
                );
                break;
            default:
                throw new IllegalReviewTypeException();
        }

        Long nextCursor = found.getLast().getReviewId();

        // return
        return CursorRes.of(nextCursor, pageSize, found);
    }
}
