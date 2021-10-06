package com.ssafy.checklist.domain.monitor.controller;

import com.ssafy.checklist.domain.common.entity.LowPriceInfo;
import com.ssafy.checklist.domain.monitor.controller.response.MonitorGetRes;
import com.ssafy.checklist.domain.monitor.controller.response.MonitorInfoGetRes;
import com.ssafy.checklist.domain.monitor.entity.Monitor;
import com.ssafy.checklist.domain.monitor.entity.MonitorPerformance;
import com.ssafy.checklist.domain.monitor.service.MonitorService;
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
@RequestMapping("/api/monitor")
@Api(value = "MonitorController", tags = "MonitorController", description = " 모니터 컨트롤러")
public class MonitorController {

    @Autowired
    MonitorService monitorService;

    @ApiOperation(value = "모든 모니터 조회", notes = "모든 모니터를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("")
    public ResponseEntity<List<MonitorGetRes>> findAllMonitor(){ return null; }

    @ApiOperation(value = "모니터 조회", notes = "모니터를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("/{pcode}")
    public ResponseEntity<MonitorGetRes> findMonitor(@PathVariable String pcode){
        /**
         * @Method Name : findMoniter
         * @작성자 : 김윤주
         * @Method 설명 : pcode에 해당하는 상품의 상세정보와 성능 분석 정보, 최저가 정보를 DB에서 받아와 전달한다.
         */

        // 상세정보
        Monitor m = monitorService.findMonitorById(pcode);

        // 분석정보
        MonitorPerformance mp = monitorService.findMonitorPerformanceById(pcode);

        // 최저가 정보...
        List<LowPriceInfo> list = monitorService.findLowPriceById(pcode);

        return new ResponseEntity<MonitorGetRes>(MonitorGetRes.of(m, mp, list), HttpStatus.OK);
    }

    @ApiOperation(value = "모니터 목록 조회", notes = "모니터를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @PostMapping("/filters")
    public ResponseEntity<List<MonitorInfoGetRes>> findMonitorList(@PageableDefault(size=10, sort="pcode", direction = Sort.Direction.DESC) Pageable pageRequest, @RequestBody Map<String, Object> filters){
        /**
         * @Method Name : findMoniterList
         * @작성자 : 김윤주
         * @Method 설명 : 필터에 부합하는 상품의 목록(pcode, 가격 등 상품의 기본정보와 성능분석 포함)을 DB에서 페이징 처리를 통해 받아와 전달한다.
         */

        System.out.println(filters);
        System.out.println(filters.get("가격대"));
        System.out.println(filters.get("화면 크기"));
        System.out.println(filters.get("해상도"));

        List<MonitorInfoGetRes> list = monitorService.findMonitorByFilters(filters, pageRequest);

        return new ResponseEntity<List<MonitorInfoGetRes>>(list, HttpStatus.OK);
    }


    @ApiOperation(value = "모니터 체크픽 조회", notes = "모니터 체크픽을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("/checkpick")
    public ResponseEntity<List<MonitorGetRes>> findCheckPick() {
        /**
         * @Method Name : findCheckPick
         * @작성자 : 이영주
         * @Method 설명 : 체크리스트에서 선정한 상위 10개 상품 목록 해당하는 상품 조회 (상품 정보, 성능 분석, 최저가 정보)
         */
        List<MonitorGetRes> monitorGetResList = monitorService.findCheckPick();
        return new ResponseEntity<List<MonitorGetRes>>(monitorGetResList, HttpStatus.OK);
    }

    @ApiOperation(value = "키워드에 해당하는 모니터 조회", notes = "키워드에 해당하는 상품을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("/search/{page}/{keyword}")
    public ResponseEntity<List<MonitorGetRes>> findAllByKeyword(@PathVariable(name = "page") int page, @PathVariable(name = "keyword") String keyword) {
        /**
         * @Method Name : findAllByKeyword
         * @작성자 : 이영주
         * @Method 설명 : 키워드를 포함하는 상품 목록 조회
         */
        List<MonitorGetRes> monitorGetResList = monitorService.findAllByKeyword(page, keyword);
        return new ResponseEntity<List<MonitorGetRes>>(monitorGetResList, HttpStatus.OK);
    }
}