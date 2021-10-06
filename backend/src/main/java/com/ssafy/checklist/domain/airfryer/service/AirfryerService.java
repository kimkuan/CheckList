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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
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

    /**
     * @Method Name : findCheckPick
     * @작성자 : 이상현
     * @Method 설명 : 에어프라이기 카테고리에서 성능이 좋은 순으로 10개 목록 조회
     */
    public List<AirfryerGetRes> findCheckPick() {
        List<AirfryerGetRes> airfryerGetResList = new ArrayList<>();
        List<AirfryerPerformance> airfryerPerformanceGetResList = airfryerPerformanceRepository.findCheckPick();

        for(AirfryerPerformance airfryerPerformance : airfryerPerformanceGetResList) {
            Airfryer airfryer = airfryerRepository.findById(airfryerPerformance.getPcode()).orElseGet(null);
            airfryerGetResList.add(AirfryerGetRes.of(airfryer, airfryerPerformance));
        }
        return airfryerGetResList;
    }

    /**
     * @Method Name : findAllByKeyword
     * @작성자 : 이상현
     * @Method 설명 : keyword를 포함하는 에어프라이기 목록 제공
     */
    public List<AirfryerGetRes> findAllByKeyword(int page, String keyword) {
        PageRequest pageRequest = PageRequest.of(page, 30, Sort.Direction.DESC, "pcode");
        Page<Airfryer> airfryers = airfryerRepository.findAllByNameContaining(keyword, pageRequest);

        List<AirfryerGetRes> airfryerGetResList = new ArrayList<>();
        int size = airfryers.getContent().size();
        for(int i = 0; i < size; i++) {
            Airfryer airfryer = airfryers.getContent().get(i);
            Optional<AirfryerPerformance> airfryerPerformance = airfryerPerformanceRepository.findById(airfryer.getPcode());
            if(airfryerPerformance.isPresent()) {
                airfryerGetResList.add(AirfryerGetRes.of(airfryer, airfryerPerformance.get()));
            }
        }

        return airfryerGetResList;
    }

}
