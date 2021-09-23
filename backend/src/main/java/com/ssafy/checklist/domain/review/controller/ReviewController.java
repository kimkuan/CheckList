package com.ssafy.checklist.domain.review.controller;

import com.ssafy.checklist.domain.review.controller.response.ReviewGetRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/review")
@Api(value = "ReviewController", tags = "ReviewController", description = " 리뷰 컨트롤러")

public class ReviewController {

    @ApiOperation(value = "모든 리뷰 조회", notes = "특정 제품에 대한 모든 리뷰를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("")
    public ResponseEntity<List<ReviewGetRes>> findAllReview(String id){
        return null;
    }

    @ApiOperation(value = "특정 키워드를 갖는 모든 리뷰 조회", notes = "제품에 대한 리뷰 중, 특정 키워드에 갖는 리뷰 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("")
    public ResponseEntity<ReviewGetRes> findAllReviewByKeyword(String keyword){
        return null;
    }

}
