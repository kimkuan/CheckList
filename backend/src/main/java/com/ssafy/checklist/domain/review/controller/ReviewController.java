package com.ssafy.checklist.domain.review.controller;

import com.ssafy.checklist.domain.review.controller.response.ReviewGetRes;
import com.ssafy.checklist.domain.review.controller.response.ReviewListGetRes;
import com.ssafy.checklist.domain.review.entity.Review;
import com.ssafy.checklist.domain.review.entity.ReviewWordCloud;
import com.ssafy.checklist.domain.review.repository.ReviewRepository;
import com.ssafy.checklist.domain.review.service.ReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/review")
@Api(value = "ReviewController", tags = "ReviewController", description = " 리뷰 컨트롤러")

public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @ApiOperation(value = "상품 리뷰 조회", notes = "pcode와 동일한 상품에 대한 모든 리뷰를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("/{pcode}/{page}")
    public ResponseEntity<ReviewListGetRes> findAllReview(@PathVariable(name = "pcode") Long pcode, @PathVariable(name = "page") int page){

        ReviewListGetRes reviewListGetRes = reviewService.findAll(pcode, page);
        return new ResponseEntity<>(reviewListGetRes, HttpStatus.OK);
    }


    @ApiOperation(value = "상품 워드 클라우드 데이터 조회", notes = "pcode와 동일한 상품에 대한 워드 클라우드 데이터를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("/wordcloud/{pcode}")
    public ResponseEntity<ReviewWordCloud> findWordCloudByKeyword(@PathVariable(name = "pcode") Long pcode){
        System.out.println("pcode :" +pcode);
        ReviewWordCloud reviewWordCloud = reviewService.findById(pcode);
        return new ResponseEntity<>(reviewWordCloud, HttpStatus.OK);
    }


    @ApiOperation(value = "특정 키워드를 갖는 모든 리뷰 조회", notes = "제품에 대한 리뷰 중, 특정 키워드에 갖는 리뷰 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("/{pcode}/{keyword}/{page}")
    public ResponseEntity<Page<Review>> findAllReviewByKeyword(@PathVariable(name = "pcode") Long pcode, @PathVariable(name = "keyword") String keyword, @PathVariable(name = "page") int page){
        System.out.println("키워드 >> " + keyword);
        Page<Review> reviews = reviewService.findReviewByKeyword(pcode, keyword, page);

        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }



}
