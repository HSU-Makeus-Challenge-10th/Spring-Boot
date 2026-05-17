package com.umc.study.domain.review.exception;

import com.umc.study.domain.review.exception.code.ReviewErrorCode;
import com.umc.study.global.apiPayload.exception.BaseException;

public class IllegalReviewTypeException extends BaseException {
    public IllegalReviewTypeException() {
        super(ReviewErrorCode.ILLEGAL_REVIEW_TYPE);
    }
}
