package com.ssafy.checklist.domain.airfryer.controller;

import com.ssafy.checklist.domain.aircleaner.controller.response.AircleanerGetRes;
import com.ssafy.checklist.domain.airfryer.controller.response.AirfryerGetRes;
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
@RequestMapping("/api/airfryer")
@Api(value = "AircleanerController", tags = "AircleanerController", description = "에어프라이어 컨트롤러")
public class AirfryerController {
    @ApiOperation(value = "모든 에어프라이어 조회", notes = "모든 에어프라이어를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("")
    public ResponseEntity<List<AirfryerGetRes>> findAllAirfryer(){

        return null;
    }

    @ApiOperation(value = "에어프라이어 조회", notes = "에어프라이어를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("")
    public ResponseEntity<AirfryerGetRes> findAirfryer(String id){

        return null;
    }
}