package com.ssafy.checklist.domain.airfryer.controller;

import com.ssafy.checklist.domain.airfryer.controller.response.AirfryerGetRes;
import com.ssafy.checklist.domain.airfryer.entity.Airfryer;
import com.ssafy.checklist.domain.airfryer.service.AirfryerService;
import com.ssafy.checklist.domain.coffeemachine.controller.response.CoffeemachineGetRes;
import com.ssafy.checklist.domain.coffeemachine.entity.Coffeemachine;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;
import java.util.List;

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
    @GetMapping("/")
    /**
    * @Method Name : findAllAirfryerByFilter
    * @작성자 : 이상현
    * @Method 설명 : 필터조건에 맞는 에어프라이기 상품 목록 조회, 상품정보와 상품성능분석점수가 포함된다.
    */
    public ResponseEntity<List<AirfryerGetRes>> findAllAirfryerByFilter(Pageable pageable, List<String> priceFilter, List<String> typeFilter,
                                                                        List<String> volumeFilter, List<String> powerFilter,  List<String> controlFilter){

        Page<AirfryerGetRes> airfryerServiceGetResList
                = airfryerService.findAirfryerListByFilter(pageable, priceFilter, typeFilter, volumeFilter, powerFilter, controlFilter);

        return null;
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
    public ResponseEntity<AirfryerGetRes> findAirfryerByPcode(@PathVariable("pcode") Long pcode){
        Airfryer airfryer = airfryerService.findAirfryerByPcode(pcode);


        return null;
    }
}