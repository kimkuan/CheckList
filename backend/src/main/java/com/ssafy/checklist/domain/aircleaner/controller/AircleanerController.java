package com.ssafy.checklist.domain.aircleaner.controller;

import com.ssafy.checklist.domain.aircleaner.controller.response.AircleanerGetRes;
import com.ssafy.checklist.domain.aircleaner.entity.Aircleaner;
import com.ssafy.checklist.domain.aircleaner.service.AircleanerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/aircleaner")
@Api(value = "AircleanerController", tags = "AircleanerController", description = "공기청정기 컨트롤러")
public class AircleanerController {

    @Autowired
    AircleanerService aircleanerService;

    @ApiOperation(value = "조건에 맞는 공기청정기 조회", notes = "조건에 맞는 공기청정기를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("/")
    public Page<Aircleaner> findAll(@PageableDefault(size = 15) Pageable pageable){
        return aircleanerService.findByFilter(pageable);
    }

    @ApiOperation(value = "공기청정기 조회", notes = "공기청정기를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("/{pcode}")
    public AircleanerGetRes findById(Long pcode){
        return aircleanerService.findById(pcode);
    }
}