package com.ssafy.checklist.domain.monitor.controller;

import com.ssafy.checklist.domain.monitor.controller.response.MoniterGetRes;
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
@RequestMapping("/api/moniter")
@Api(value = "MoniterController", tags = "MoniterController", description = " 모니터 컨트롤러")
public class MoniterController {

    @ApiOperation(value = "모든 모니터 조회", notes = "모든 모니터를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("")
    public ResponseEntity<List<MoniterGetRes>> findAllMoniter(){
        return null;
    }

    @ApiOperation(value = "모니터 조회", notes = "모니터를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MoniterGetRes> findMoniter(String id){
        return null;
    }

}