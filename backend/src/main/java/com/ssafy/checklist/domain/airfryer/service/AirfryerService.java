package com.ssafy.checklist.domain.airfryer.service;

import com.ssafy.checklist.domain.airfryer.controller.response.AirfryerGetRes;
import com.ssafy.checklist.domain.airfryer.entity.Airfryer;
import com.ssafy.checklist.domain.airfryer.entity.AirfryerPerformance;
import com.ssafy.checklist.domain.airfryer.repository.AirfryerPerformanceRepository;
import com.ssafy.checklist.domain.airfryer.repository.AirfryerRepository;
import com.ssafy.checklist.domain.common.entity.LowPriceInfo;
import com.ssafy.checklist.domain.common.repository.LowPriceInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class AirfryerService {
    @Autowired
    AirfryerRepository airfryerRepository;

    @Autowired
    AirfryerPerformanceRepository airfryerPerformanceRepository;

    @Autowired
    LowPriceInfoRepository lowPriceInfoRepository;

    public Page<AirfryerGetRes> findAirfryerListByFilter(Pageable pageable, List<String> priceFilter, List<String> typeFilter,
                                                         List<String> volumeFilter, List<String> powerFilter, List<String> controlFilter) {

        Page<AirfryerGetRes> page = findAirfryerListByFilter(pageable, priceFilter, typeFilter, volumeFilter, powerFilter, controlFilter);

        return page;
    }

    /**
    * @Method Name : findAirfryerByPcode
    * @작성자 : 이상현
    * @Method 설명 : pcode에 맞는 에어프라이기 조회
    */
    public Airfryer findAirfryerByPcode(long pcode) {
        Airfryer airfryer = airfryerRepository.findById(pcode).orElseGet(null);
        return airfryer;
    }
    
    /**  
    * @Method Name : findAirfryerPerformanceByPcode  
    * @작성자 : 이상현
    * @Method 설명 : pcode에 맞는 에어프라이기 성능 조회
    */
    public AirfryerPerformance findAirfryerPerformanceByPcode(long pcode) {
        AirfryerPerformance airfryerPerformance = airfryerPerformanceRepository.findById(pcode).orElse(null);
        return airfryerPerformance;
    }

    /**
    * @Method Name : findLowPriceInfoByPcode
    * @작성자 : 이상현
    * @Method 설명 : pcode에 맞는 커피머신 최저가 사이트 정보 제공
    */
    public List<LowPriceInfo> findLowPriceInfoByPcode(long pcode) {
        List<LowPriceInfo> lowPriceInfoList = lowPriceInfoRepository.findAllByPcode(pcode).orElse(null);
        return lowPriceInfoList;
    }
}
