package com.ssafy.checklist.domain.review.repository;

import com.ssafy.checklist.domain.review.controller.response.ReviewListGetRes;
import com.ssafy.checklist.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {

    @Query(
            value= "SELECT avg (r.score) FROM review r WHERE r.pcode = :pcode",
            nativeQuery = true
    )
    double getAvgScoreByPcode(@Param("pcode") Long pcode);

    Optional<Page<Review>> findAllByPcodeAndAndContentContaining(Long pcode, String keyword, Pageable pageable);

    Optional<List<Review>> findAllByPcodeAndContentContaining(Long pcode, String keyword);

    Optional<Page<Review>> findAllByPcode(Long pcode, Pageable pageable);
}
