package com.ssafy.checklist.domain.review.repository;

import com.ssafy.checklist.domain.review.controller.response.ReviewListGetRes;
import com.ssafy.checklist.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {

    ReviewListGetRes findAll(Long pcode, Pageable pageable);
}
