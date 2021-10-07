package com.ssafy.checklist.domain.airfryer.controller;

import com.ssafy.checklist.domain.airfryer.controller.response.AirfryerGetRes;
import com.ssafy.checklist.domain.airfryer.controller.response.AirfryerInfoGetRes;
import com.ssafy.checklist.domain.airfryer.entity.Airfryer;
import com.ssafy.checklist.domain.airfryer.entity.AirfryerPerformance;
import com.ssafy.checklist.domain.airfryer.service.AirfryerService;
import com.ssafy.checklist.domain.coffeemachine.controller.response.CoffeemachineGetRes;
import com.ssafy.checklist.domain.coffeemachine.entity.Coffeemachine;
import com.ssafy.checklist.domain.common.entity.LowPriceInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/airfryer")
@Api(value = "AirfryerController", tags = "AirfryerController", description = "에어프라이어 컨트롤러")
public class AirfryerController {

    @Autowired
    AirfryerService airfryerService;

    @ApiOperation(value = "페이징 & 필터에 맞는 에어프라이어 조회", notes = "페이징, 필터조건에 맞는 에어프라이어를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @PostMapping("/filters")
    /**
     * @Method Name : findAllAirfryerByFilter
     * @작성자 : 이상현
     * @Method 설명 : 필터조건에 맞는 에어프라이기 상품 목록 조회, 상품정보와 상품성능분석점수가 포함된다.
     */
    public ResponseEntity<List<AirfryerGetRes>> findAllAirfryerByFilter(@PageableDefault(size=10) Pageable pageable, @RequestBody Map<String, Object> filters){

        System.out.println(filters);
        List<AirfryerGetRes> airfryerGetResList = airfryerService.findAirfryerListByFilter(pageable, filters);

        return new ResponseEntity<>(airfryerGetResList, HttpStatus.OK);
    }

    @ApiOperation(value = "에어프라이어 조회", notes = "에어프라이어를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("/{pcode}")
    /**
    * @Method Name : findAirfryerByPcode
    * @작성자 : 이상현
    * @Method 설명 : pcode에 해당하는 상품 조회 (상품 정보, 성능 분석, 최저가 정보)
    */
    public ResponseEntity<AirfryerInfoGetRes> findAirfryerByPcode(@PathVariable("pcode") Long pcode){
        Airfryer airfryer = airfryerService.findAirfryerByPcode(pcode);
        AirfryerPerformance airfryerPerformance = airfryerService.findAirfryerPerformanceByPcode(pcode);
        List<LowPriceInfo> lowPriceInfoList = airfryerService.findLowPriceInfoByPcode(pcode);

        return new ResponseEntity<>(AirfryerInfoGetRes.of(airfryer, airfryerPerformance, lowPriceInfoList), HttpStatus.OK);
    }

    @ApiOperation(value = "에어프라이기 체크픽 조회", notes = "에어프라이기 체크픽을 조회한다. (10개) ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("/checkpick")
    /**
    * @Method Name : findCheckPick
    * @작성자 : 이상현
    * @Method 설명 :
    */
    public ResponseEntity<List<AirfryerGetRes>> findCheckPick() {
        List<AirfryerGetRes> airfryerGetResList = airfryerService.findCheckPick();
        return new ResponseEntity<>(airfryerGetResList, HttpStatus.OK);
    }

    @ApiOperation(value = "키워드에 해당하는 에어프라이기 조회", notes = "키워드에 해당하는 상품을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("/search/{page}/{keyword}")
    /**
    * @Method Name : findAllByKeyword
    * @작성자 : 이상현
    * @Method 설명 :
    */
    public ResponseEntity<List<AirfryerGetRes>> findAllByKeyword(@PathVariable(name = "page") int page, @PathVariable(name = "keyword") String keyword) {
        List<AirfryerGetRes> airfryerGetResList = airfryerService.findAllByKeyword(page, keyword);
        return new ResponseEntity<>(airfryerGetResList, HttpStatus.OK);
    }

}