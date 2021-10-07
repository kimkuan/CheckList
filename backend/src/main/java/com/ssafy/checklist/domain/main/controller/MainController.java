package com.ssafy.checklist.domain.main.controller;

import com.ssafy.checklist.domain.main.controller.response.FilterGetRes;
import com.ssafy.checklist.domain.main.controller.response.ProductGetRes;
import com.ssafy.checklist.domain.main.entity.Filter;
import com.ssafy.checklist.domain.main.entity.Product;
import com.ssafy.checklist.domain.main.service.MainService;
import com.ssafy.checklist.domain.review.controller.response.ReviewGetRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;

@RestController
@RequestMapping("/api/main")
@Api(value = "MainController", tags = "MainController", description = " 메인 컨트롤러")
public class MainController {

    @Autowired
    MainService mainService;

    @ApiOperation(value = "카테고리별 랭킹 조회", notes = "특정 카테고리의 상품목록을 랭킹순으로 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("/category")
    public ResponseEntity<List<ProductGetRes>> findAllRankByCategory(String category){

        /**
         * @Method Name : findAllProductByKeyword
         * @작성자 : 김윤주
         * @Method 설명 : 각 도메인에서 따로 구현
         */

        return null;
    }

    @ApiOperation(value = "키워드 상품 조회", notes = "특정 키워드에 맞는 상품 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("/{keyword}")
    public ResponseEntity<List<ProductGetRes>> findAllProductByKeyword(String keyword){
        // keyword에는 상품명, 상품모델명, 브랜드가 올 수 있다.

        /**
        * @Method Name : findAllProductByKeyword
        * @작성자 : 김윤주
        * @Method 설명 : 각 도메인에서 따로 구현
        */

        return null;
    }

    @ApiOperation(value = "상품 조회수 증가", notes = "선택한 상품 조회수를 증가한다. ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회수 증가"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @PutMapping("/click/{pcode}")
    public ResponseEntity updateProductHit(@PathVariable("pcode") String pcode){
        /**  
        * @Method Name : updateProductHit  
        * @작성자 : 김윤주 
        * @Method 설명 : 상품의 조회수 증가시킴. 반환값은 X.
        */

        mainService.updateProductHit(pcode);

        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "베스트 상품 목록", notes = "전품목 베스트상품 목록을 3개까지 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("/best")
    public ResponseEntity<List<ProductGetRes>> findAllBestProduct(){

        /**
        * @Method Name : findAllBestProduct
        * @작성자 : 김윤주
        * @Method 설명 : 조회수 상위 3개 상품의 정보를 가져와서 반환함.
        */

        List<ProductGetRes> list = mainService.findBestProduct();

        return new ResponseEntity<List<ProductGetRes>>(list, HttpStatus.OK);
    }

    @ApiOperation(value = "카테고리 필터 옵션 조회", notes = "카테고리에 맞는 옵션 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("/filter/{category}")
    public ResponseEntity<Filter> findFilterByCategory(@PathVariable("category")String keyword){
        /**
        * @Method Name : findFilterByCategory
        * @작성자 : 김윤주
        * @Method 설명 : 카테고리에 맞는 필터 옵션을 조회하여 반환함.
        */

        Filter filter = mainService.findFilterByCategory(keyword);

        return new ResponseEntity<Filter>(filter, HttpStatus.OK);
    }
}
