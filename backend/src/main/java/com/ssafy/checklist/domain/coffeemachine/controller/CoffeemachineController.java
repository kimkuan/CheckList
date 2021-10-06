package com.ssafy.checklist.domain.coffeemachine.controller;

import com.ssafy.checklist.domain.coffeemachine.controller.response.CoffeemachineGetRes;
import com.ssafy.checklist.domain.coffeemachine.controller.response.CoffeemachineInfoGetRes;
import com.ssafy.checklist.domain.coffeemachine.entity.Coffeemachine;
import com.ssafy.checklist.domain.coffeemachine.entity.CoffeemachinePerformance;
import com.ssafy.checklist.domain.coffeemachine.service.CoffeemachineService;
import com.ssafy.checklist.domain.common.entity.LowPriceInfo;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/coffeemachine")
@Api(value = "CoffeemachineController", tags = "CoffeemachineController",
        description = "커피머신 컨트롤러")
public class CoffeemachineController {

    @Autowired
    CoffeemachineService coffeemachineService;

    @ApiOperation(value = "페이징 & 필터에 맞는 커피머신 조회", notes = "페이지, 필터조건에 맞는 커피머신 목록 조회 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @PostMapping("/filters")
    /**
     * @Method Name : findCoffeemachineByFilter
     * @작성자 : 이영주
     * @Method 설명 : 필터조건에 맞는 카테고리가 커피머신인 상품 목록 조회, 상품정보와 상품성능분석점수가 포함된다.
     */
    public ResponseEntity<List<CoffeemachineGetRes>> findCoffeemachineByFilter(@PageableDefault(size = 10, sort = "name", direction = Sort.Direction.DESC) Pageable pageable, @RequestBody Map<String, Object> filters){
        List<CoffeemachineGetRes> coffeemachineGetResList = coffeemachineService.findCoffeemachineListByFilter(pageable, filters);
        return new ResponseEntity<>(coffeemachineGetResList, HttpStatus.OK);
    }


    @ApiOperation(value = "커피머신 조회", notes = "커피머신을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("/{pcode}")
    /**
    * @Method Name : findCoffeemachineByPcode
    * @작성자 : 이영주
    * @Method 설명 : pcode에 해당하는 상품 조회 (상품 정보, 성능 분석, 최저가 정보)
    */
    public ResponseEntity<CoffeemachineInfoGetRes> findCoffeemachineByPcode(@PathVariable("pcode") Long pcode){
        Coffeemachine coffeemachine = coffeemachineService.findCoffeemachineByPcode(pcode);
        CoffeemachinePerformance coffeemachinePerformance = coffeemachineService.findCoffeemachinePerformanceByPcode(pcode);
        List<LowPriceInfo> lowPriceInfoList = coffeemachineService.findLowPriceInfoByPcode(pcode);
        return new ResponseEntity<>(CoffeemachineInfoGetRes.of(coffeemachine, coffeemachinePerformance, lowPriceInfoList), HttpStatus.OK);
    }

    @ApiOperation(value = "커피머신 체크픽 조회", notes = "커피머신 체크픽을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("/checkpick")
    /**
     * @Method Name : findCheckPick
     * @작성자 : 이영주
     * @Method 설명 : 체크리스트에서 선정한 상위 10개 상품 목록 해당하는 상품 조회 (상품 정보, 성능 분석, 최저가 정보)
     */
    public ResponseEntity<List<CoffeemachineGetRes>> findCheckPick() {
        List<CoffeemachineGetRes> coffeemachineGetResList = coffeemachineService.findCheckPick();
        return new ResponseEntity<>(coffeemachineGetResList, HttpStatus.OK);
    }

    @ApiOperation(value = "키워드에 해당하는 커피머신 조회", notes = "키워드에 해당하는 상품을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("/search/{page}/{keyword}")
    /**
     * @Method Name : findAllByKeyword
     * @작성자 : 이영주
     * @Method 설명 : 키워드를 포함하는 상품 목록 조회
     */
    public ResponseEntity<List<CoffeemachineGetRes>> findAllByKeyword(@PathVariable(name = "page") int page, @PathVariable(name = "keyword") String keyword) {
        List<CoffeemachineGetRes> coffeemachineGetResList = coffeemachineService.findAllByKeyword(page, keyword);
        return new ResponseEntity<>(coffeemachineGetResList, HttpStatus.OK);
    }
}