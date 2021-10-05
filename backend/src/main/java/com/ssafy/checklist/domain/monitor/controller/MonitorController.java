package com.ssafy.checklist.domain.monitor.controller;

import com.ssafy.checklist.domain.monitor.controller.response.MonitorGetRes;
import com.ssafy.checklist.domain.monitor.entity.Monitor;
import com.ssafy.checklist.domain.monitor.service.MonitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
         * @Method 설명 : pcode에 해당하는 상품의 상세정보와 최저가 정보를 DB에서 받아와 전달한다.
         */

        // 상세정보
        Monitor m = monitorService.findMonitorById(pcode);

        // 최저가 정보...
        

        return new ResponseEntity<MonitorGetRes>(MonitorGetRes.of(m), HttpStatus.OK);
    }

}