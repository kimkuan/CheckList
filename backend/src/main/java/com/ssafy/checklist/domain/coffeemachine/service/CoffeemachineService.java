package com.ssafy.checklist.domain.coffeemachine.service;

import com.ssafy.checklist.domain.coffeemachine.controller.response.CoffeemachineGetRes;
import com.ssafy.checklist.domain.coffeemachine.controller.response.CoffeemachineInfoGetRes;
import com.ssafy.checklist.domain.coffeemachine.entity.Coffeemachine;
import com.ssafy.checklist.domain.coffeemachine.entity.CoffeemachinePerformance;
import com.ssafy.checklist.domain.coffeemachine.repository.CoffeemachinePerformanceRepository;
import com.ssafy.checklist.domain.coffeemachine.repository.CoffeemachineRepository;
import com.ssafy.checklist.domain.common.entity.LowPriceInfo;
import com.ssafy.checklist.domain.common.repository.LowPriceInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class CoffeemachineService {
    @Autowired
    CoffeemachineRepository coffeemachineRepository;

    @Autowired
    CoffeemachinePerformanceRepository coffeemachinePerformanceRepository;

    @Autowired
    LowPriceInfoRepository lowPriceInfoRepository;

    /**  
    * @Method Name : findCoffeemachineListByFilter
    * @작성자 : 이영주
    * @Method 설명 : 주어진 페이지번호, 필터 조건에 맞는 커피머신 목록 제공
    */
    public Page<CoffeemachineGetRes> findCoffeemachineListByFilter(Pageable pageable, List<String> priceFilter, List<String> pressureFilter,
                                                                   List<String> heatFilter, List<String> waterFilter) {

        // 커피머신 목록 가져오고
        List<Coffeemachine> coffeemachinelist;
        Page<CoffeemachineGetRes> coffeemachineGetResList = null;
        // for문으로 그에 맞는 성능 가져와서 return?
        // 그냥 목록 보여주기
        if(priceFilter.get(0).equals("전체") && pressureFilter.get(0).equals("전체") &&
                heatFilter.get(0).equals("전체") && waterFilter.get(0).equals("전체")) {
//            Specification<Coffeemachine> filter = Specification.where()
//            coffeemachinelist = coffeemachineRepository.findAll(filter, pageable);
        }
        return coffeemachineGetResList;
    }

    /**  
    * @Method Name : findCoffeemachineByPcode
    * @작성자 : 이영주
    * @Method 설명 : pcode에 맞는 커피머신 제공
    */
    public Coffeemachine findCoffeemachineByPcode(Long pcode) {
        Optional<Coffeemachine> coffeemachine = coffeemachineRepository.findById(pcode);

        if(coffeemachine.isPresent()) {
            return coffeemachine.get();
        }
        return null;
    }

    /**
     * @Method Name : findCoffeemachinePerformanceByPcode  
     * @작성자 : 이영주
     * @Method 설명 : pcode에 맞는 커피머신 성능 제공
     */
    public CoffeemachinePerformance findCoffeemachinePerformanceByPcode(Long pcode) {
        Optional<CoffeemachinePerformance> coffeemachinePerformance = coffeemachinePerformanceRepository.findById(pcode);

        if(coffeemachinePerformance.isPresent()) {
            return coffeemachinePerformance.get();
        }
        return null;
    }

    /**
     * @Method Name : findLowPriceInfoByPcode  
     * @작성자 : 이영주
     * @Method 설명 : pcode에 맞는 커피머신 최저가 사이트 정보 제공
     */
    public List<LowPriceInfo> findLowPriceInfoByPcode(Long pcode) {
        Optional<List<LowPriceInfo>> lowPriceInfo = lowPriceInfoRepository.findAllByPcode(pcode);

        if(lowPriceInfo.isPresent()) {
            return lowPriceInfo.get();
        }
        return null;
    }
}
