package com.ssafy.checklist.domain.foodprocessor.controller;

import com.ssafy.checklist.domain.aircleaner.controller.response.AircleanerGetRes;
import com.ssafy.checklist.domain.foodprocessor.controller.response.FoodprocessorGetRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/foodprocessor")
@Api(value = "FoodprocessorController", tags = "FoodprocessorController", description = "음식물처리기 컨트롤러")
public class FoodprocessorController {

    @ApiOperation(value = "모든 음식물처리기 조회", notes = "모든 음식물처리기 조회한다. 필터링이 포함되어있다면 쿼리스트링으로 필터 조건을 넣어준다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("")
    public ResponseEntity<FoodprocessorGetRes> findAllCategory(){

        return null;
    }

    @ApiOperation(value = "모든 음식물처리기 조회", notes = "모든 공기청정기를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AircleanerGetRes> findCategory(@PathVariable String id){

        return null;
    }

}
