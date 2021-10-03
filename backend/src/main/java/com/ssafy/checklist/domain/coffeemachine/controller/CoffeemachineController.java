package com.ssafy.checklist.domain.coffeemachine.controller;

import com.ssafy.checklist.domain.coffeemachine.controller.response.CoffeemachineGetRes;
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
@RequestMapping("/api/coffeemachine")
@Api(value = "CoffeemachineController", tags = "CoffeemachineController", description = "커피머신 컨트롤러")
public class CoffeemachineController {
    @ApiOperation(value = "모든 커피머신 조회", notes = "모든 커피머신을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("")
    public ResponseEntity<List<CoffeemachineGetRes>> findAllCoffeemachine(){

        return null;
    }

    @ApiOperation(value = "커피머신 조회", notes = "커피머신을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CoffeemachineGetRes> findCoffeemachine(String id){

        return null;
    }
}