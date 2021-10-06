package com.ssafy.checklist.domain.review.repository;

import com.ssafy.checklist.domain.review.entity.ReviewWordCloud;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewWordCloudRepository extends JpaRepository<ReviewWordCloud, Long> {
}
