package com.ssafy.checklist.domain.foodprocessor.controller;

import com.ssafy.checklist.domain.aircleaner.controller.response.AircleanerGetRes;
import com.ssafy.checklist.domain.foodprocessor.controller.response.FoodprocessorGetRes;
import com.ssafy.checklist.domain.foodprocessor.controller.response.FoodprocessorInfoGetRes;
import com.ssafy.checklist.domain.foodprocessor.entity.Foodprocessor;
import com.ssafy.checklist.domain.foodprocessor.service.FoodprocessorService;
import com.ssafy.checklist.domain.monitor.controller.response.MonitorInfoGetRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/foodprocessor")
@Api(value = "FoodprocessorController", tags = "FoodprocessorController", description = "음식물처리기 컨트롤러")
public class FoodprocessorController {

    @Autowired
    FoodprocessorService foodprocessorService;

//    @ApiOperation(value = "모든 음식물처리기 조회", notes = "모든 음식물처리기 조회한다.")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "조회 성공"),
//            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
//            @ApiResponse(code = 500, message = "서버 에러 발생")
//    })
//    @GetMapping("")
//    public ResponseEntity<List<FoodprocessorGetRes>> findAllFoodprocessor(){
//        /**
//        * @methodName : findAllCategory
//        * @작성자 : 권영린
//        * @Class 설명 : 음식물처리기 상품 리스트를 조회한다. 필터링이 포함되어있을 수 있다.
//        */
//        List<FoodprocessorGetRes> foodprocessorGetResList = foodprocessorService.findAllFoodprocessor();
//
//        return new ResponseEntity<List<FoodprocessorGetRes>>(foodprocessorGetResList, HttpStatus.OK);
//    }

    @ApiOperation(value = "음식물처리기 상세 조회", notes = "pcode로 음식물처리기 상세정보를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("/{pcode}")
    public ResponseEntity<FoodprocessorGetRes> findFoodprocessor(@PathVariable String pcode){
        /**
        * @methodName : findFoodprocessor
        * @작성자 : 권영린
        * @Class 설명 : pcode를 전달받으면 해당 id에 맞는 상품 상세정보를 반환한다.
        */
        FoodprocessorGetRes foodprocessorGetRes = foodprocessorService.findFoodprocessorById(Long.parseLong(pcode));
        if (foodprocessorGetRes != null) {
            return new ResponseEntity<FoodprocessorGetRes>(foodprocessorGetRes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "음식물처리기 목록 조회", notes = "조건에 따른 음식물처리기목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @PostMapping("/filters")
    public ResponseEntity<List<FoodprocessorInfoGetRes>> findFoodprocessorList(
            @PageableDefault(size=10, sort="pcode", direction = Sort.Direction.DESC) Pageable pageRequest,
            @RequestBody Map<String, Object> filters) {
        /**
         * @methodName : findFoodprocessorList
         * @작성자 : 권영린
         * @Class 설명 : 필터링 정보를 반환받고 해당 조건에 해당하는 상품리스트를 반환한다.
         */

        List<FoodprocessorInfoGetRes> list = foodprocessorService.findFoodprocessorByFilters(filters, pageRequest);


        return new ResponseEntity<List<FoodprocessorInfoGetRes>>(list, HttpStatus.OK);
    }

}
