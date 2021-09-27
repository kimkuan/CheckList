package com.ssafy.checklist.domain.main.controller;

import com.ssafy.checklist.domain.log.controller.response.LogBestProductGetRes;
import com.ssafy.checklist.domain.main.controller.response.ProductGetRes;
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
@RequestMapping("/api/main")
@Api(value = "MainController", tags = "MainController", description = " 메인 컨트롤러")

public class MainController {

    @ApiOperation(value = "카테고리별 랭킹 조회", notes = "특정 카테고리의 상품목록을 랭킹순으로 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("")
    public ResponseEntity<List<ProductGetRes>> findAllRankByCategory(String category){

        return null;
    }

    @ApiOperation(value = "키워드 상품 조회", notes = "특정 키워드에 맞는 상품 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("")
    public ResponseEntity<List<ProductGetRes>> findAllProductByKeyword(String keyword){
        // keyword에는 상품명, 상품모델명, 브랜드가 올 수 있다.

        return null;
    }

    @ApiOperation(value = "실시간 베스트 상품 목록", notes = "실시간 전품목 베스트상품 목록을 3개까지 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("")
    public ResponseEntity<List<ProductGetRes>> findAllBestProduct(){

        return null;
    }
}