package com.ssafy.checklist.domain.aircleaner.controller;

import com.ssafy.checklist.domain.aircleaner.controller.response.AircleanerGetRes;
import com.ssafy.checklist.domain.aircleaner.controller.response.AircleanerInfoGetRes;
import com.ssafy.checklist.domain.aircleaner.entity.Aircleaner;
import com.ssafy.checklist.domain.aircleaner.entity.AircleanerPerformance;
import com.ssafy.checklist.domain.aircleaner.service.AircleanerService;
import com.ssafy.checklist.domain.common.entity.LowPriceInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/aircleaner")
@Api(value = "AircleanerController", tags = "AircleanerController", description = "공기청정기 컨트롤러")
public class AircleanerController {

    @Autowired
    AircleanerService aircleanerService;

    @ApiOperation(value = "페이징 & 필터에 맞는 공기청정기 조회", notes = "페이지, 필터조건에 맞는 공기청정기 목록 조회 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @PostMapping("/filters")
    /**
     * @Method Name : findAllByFilter
     * @작성자 : 김선혜
     * @Method 설명 : 필터조건에 맞는 카테고리가 공기청정기인 상품 목록 조회, 상품정보와 상품성능분석점수가 포함된다.
     */
    public ResponseEntity<List<AircleanerGetRes>> findAircleanerByFilter
            (@PageableDefault(size = 10, sort = "name", direction = Sort.Direction.DESC) Pageable pageable,
             @RequestBody Map<String, Object> filters){
        List<AircleanerGetRes> aircleanerGetResList = aircleanerService.findAircleanerByFilters(pageable, filters);
        return new ResponseEntity<>(aircleanerGetResList, HttpStatus.OK);
    }

    @ApiOperation(value = "공기청정기 조회", notes = "공기청정기를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("/{pcode}")
    /**
     * @Method Name : findByPcode
     * @작성자 : 김선혜
     * @Method 설명 : pcode에 해당하는 상품 조회 (상품 정보, 성능 분석, 최저가 정보)
     */
    public ResponseEntity<AircleanerInfoGetRes> findAircleanerByPcode(@PathVariable("pcode") Long pcode){
        Aircleaner aircleaner = aircleanerService.findAircleanerByPcode(pcode);
        AircleanerPerformance aircleanerPerformance = aircleanerService.findAircleanerPerformanceByPcode(pcode);
        List<LowPriceInfo> lowPriceInfoList = aircleanerService.findLowPriceByPcode(pcode);
        return new ResponseEntity<>(AircleanerInfoGetRes.from(aircleaner, aircleanerPerformance, lowPriceInfoList), HttpStatus.OK);
    }
}