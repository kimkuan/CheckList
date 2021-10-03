package com.ssafy.checklist.domain.aircleaner.controller;

import com.ssafy.checklist.domain.aircleaner.controller.response.AircleanerGetRes;
import com.ssafy.checklist.domain.aircleaner.service.AircleanerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/api/aircleaner")
@Api(value = "AircleanerController", tags = "AircleanerController", description = "공기청정기 컨트롤러")
public class AircleanerController {
    @Autowired
    AircleanerService aircleanerService;

    @ApiOperation(value = "모든 공기청정기 조회", notes = "모든 공기청정기를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("")
    public List<AircleanerGetRes> findAllAircleaner(){
        return aircleanerService.findAll();
    }

    @ApiOperation(value = "공기청정기 조회", notes = "공기청정기를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AircleanerGetRes> findAircleaner(String id){

        return null;
    }
}