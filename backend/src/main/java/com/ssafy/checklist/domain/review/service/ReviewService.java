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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ReviewWordCloudRepository reviewWordCloudRepository;

    public ReviewListGetRes findAll(Long pcode, int page) {

        PageRequest pageRequest = PageRequest.of(page,10, Sort.Direction.DESC, "id");
        Optional<Page<Review>> reviews = reviewRepository.findAllByPcode(pcode, pageRequest);
        long avgScore = (long) reviewRepository.getAvgScoreByPcode(pcode);
        if(reviews.isPresent()) {
            return ReviewListGetRes.of(pcode, Long.valueOf(avgScore), reviews.get());
        }
        return null;
    }

    public ReviewWordCloud findById(Long pcode) {
        Optional<ReviewWordCloud> reviewWordCloud = reviewWordCloudRepository.findById(pcode);
        if(reviewWordCloud.isPresent()) {
            return reviewWordCloud.get();
        }
        return null;
    }

//    public Page<Review> findReviewByKeyword(Long pcode, String keyword, int page) {
//        System.out.println("pcode : "+pcode);
//        System.out.println("keyword : "+keyword);
//        System.out.println("page : "+page);
//        PageRequest pageRequest = PageRequest.of(page,10, Sort.Direction.DESC, "id");
//        Optional<Page<Review>> reviews = reviewRepository.findAllByPcodeAndAndContentContaining(pcode, keyword, pageRequest);
//        if(reviews.isPresent()) {
//            return reviews.get();
//        }
//        return null;
//    }

    public List<Review> findReviewByKeyword(Long pcode, String keyword) {
        System.out.println("pcode : "+pcode);
        System.out.println("keyword : "+keyword);
        Optional<List<Review>> reviews = reviewRepository.findAllByPcodeAndContentContaining(pcode, keyword);
        if(reviews.isPresent()) {
            return reviews.get();
        }
        return null;
    }
}
