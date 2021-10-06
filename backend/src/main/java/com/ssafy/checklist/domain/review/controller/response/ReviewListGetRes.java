package com.ssafy.checklist.domain.review.controller.response;

import com.ssafy.checklist.domain.review.entity.Review;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class ReviewListGetRes {

    @ApiModelProperty
    private Long pcode;

    @ApiModelProperty
    private String category;

    @ApiModelProperty
    private long avgScore;      // 평균 별점 (100점 만점 기분)

    @ApiModelProperty
    private Page<Review> reviewList;

    public static ReviewListGetRes of(Long pcode, String category, Long avgScore, Page<Review> reviewList) {
        ReviewListGetRes res = new ReviewListGetRes();

        res.setPcode(pcode);
        res.setCategory(category);
        res.setAvgScore(avgScore);
        res.setReviewList(reviewList);
        return res;
    }

}

