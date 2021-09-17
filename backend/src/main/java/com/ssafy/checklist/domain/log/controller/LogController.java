package com.ssafy.checklist.domain.log.controller;

import com.ssafy.checklist.domain.log.controller.response.LogBestProductGetRes;
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
@RequestMapping("/api/foodprocessor")
@Api(value = "FoodprocessorController", tags = "FoodprocessorController", description = "음식물처리기 컨트롤러")
public class LogController {

    @ApiOperation(value = "실시간 검색어 목록", notes = "실시간 검색어 목록을 10개까지 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("")
    public ResponseEntity<List<String>> findAllTrendingTopic(){

        return null;
    }

    @ApiOperation(value = "실시간 베스트 상품 목록", notes = "실시간 전품목 베스트상품 목록을 3개까지 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("")
    public ResponseEntity<List<LogBestProductGetRes>> findAllBestProduct(){

        return null;
    }

}
