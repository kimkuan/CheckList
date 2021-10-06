package com.ssafy.checklist.domain.review.service;

import com.ssafy.checklist.domain.review.controller.response.ReviewListGetRes;
import com.ssafy.checklist.domain.review.entity.Review;
import com.ssafy.checklist.domain.review.entity.ReviewWordCloud;
import com.ssafy.checklist.domain.review.repository.ReviewRepository;
import com.ssafy.checklist.domain.review.repository.ReviewWordCloudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ReviewWordCloudRepository reviewWordCloudRepository;

    public ReviewListGetRes findAll(Long pcode, Pageable pageable) {
        return null;
    }

    public ReviewWordCloud findById(Long pcode) {
        Optional<ReviewWordCloud> reviewWordCloud = reviewWordCloudRepository.findById(pcode);
    }

    public Page<Review> findReviewByKeyword(Long pcode, String keyword, Pageable pageable) {
        return null;
    }
}
