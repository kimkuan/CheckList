package com.ssafy.checklist.domain.coffeemachine.service;

import com.ssafy.checklist.domain.coffeemachine.controller.response.CoffeemachineGetRes;
import com.ssafy.checklist.domain.coffeemachine.controller.response.CoffeemachineInfoGetRes;
import com.ssafy.checklist.domain.coffeemachine.entity.Coffeemachine;
import com.ssafy.checklist.domain.coffeemachine.entity.CoffeemachinePerformance;
import com.ssafy.checklist.domain.coffeemachine.repository.CoffeemachinePerformanceRepository;
import com.ssafy.checklist.domain.coffeemachine.repository.CoffeemachineRepository;
import com.ssafy.checklist.domain.coffeemachine.repository.CoffeemachineSpecification;
import com.ssafy.checklist.domain.common.entity.LowPriceInfo;
import com.ssafy.checklist.domain.common.repository.LowPriceInfoRepository;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
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
    public List<CoffeemachineGetRes> findCoffeemachineListByFilter(int page, List<String> priceFilter, List<String> pressureFilter,
                                                                   List<String> heatFilter, List<String> waterFilter) {
        
        // page > 0부터 시작
        PageRequest pageRequest = PageRequest.of(page, 15, Sort.Direction.DESC, "pcode");
        // 커피머신 목록 가져오고
        Page<Coffeemachine> coffeemachinelist = null;
        List<CoffeemachineGetRes> coffeemachineGetResList = new ArrayList<>();
        // for문으로 그에 맞는 성능 가져와서 return?
        // 그냥 목록 보여주기
        if(priceFilter.get(0).equals("전체") && pressureFilter.get(0).equals("전체") &&
                heatFilter.get(0).equals("전체") && waterFilter.get(0).equals("전체")) {
//            Specification<Coffeemachine> filter = Specification.where(CoffeemachineSpecification.inType());
            coffeemachinelist = coffeemachineRepository.findAll(pageRequest);
            System.out.println("그냥 목록 보여주기");
            for(Coffeemachine coffeemachine : coffeemachinelist) {
                System.out.println(coffeemachine.getPcode());
            }
        } else {        // 모두 전체가 아닐 경우 -> 필터링 작업
            Specification<Coffeemachine> filter = Specification.where(CoffeemachineSpecification.all());

            // 별개의 항목은 and
            // 가격대 => 중복 선택 가능 (각 항목끼리는 or)
            if(!priceFilter.get(0).equals("전체")) {
                for(String price : priceFilter) {
                    System.out.println(price);
                    price = price.replace("만원","");      // L 붙은거 제거
                    String[] range = price.split("~");


                    System.out.println("가격정보 ???");
                    for (String r : range) {
                        System.out.println(r);
                    }
                    if(range[0].equals("")) {
                        filter = filter.and(CoffeemachineSpecification.ltPrice(100000));
                    } else if(range.length == 2) {
                        int price1 = Integer.parseInt(range[0]) * 10000;
                        int price2 = Integer.parseInt(range[1]) * 10000;
                        filter = filter.and(CoffeemachineSpecification.betweenPrice(price1, price2));
                    } else {
                        filter = filter.and(CoffeemachineSpecification.gtPrice(300000));
                    }
                }
            }

            // 펌프압력 => 중복 선택 가능
            if(!pressureFilter.get(0).equals("전체")) {
//                for(String pressure : pressureFilter) {
//                    System.out.println("펌프압력  >>> " + pressure);
//                    filter = filter.or(CoffeemachineSpecification.eqPressure(pressure));
//                }
                filter = filter.and(CoffeemachineSpecification.eqPressure(pressureFilter));
            }



            // 예열시간 => 하나만 선택 가능 ["전체", "~30초", "~40초", "~50초"] 이런식으로 들어옴
            if(!heatFilter.get(0).equals("전체")) {
                int heatTime = Integer.parseInt(heatFilter.get(0).substring(1, 3));
                System.out.println("예열시간 >> " + heatTime);
                filter = filter.and(CoffeemachineSpecification.betweenHeatTime(heatTime));
            }

            // 물통용량 => 중복 선택 가능
            if(!waterFilter.get(0).equals("전체")) {
                for(String water : waterFilter) {
                    System.out.println(water);
                    water = water.replace("L","");      // L 붙은거 제거
                    String[] range = water.split("~");

                    if(range[0].equals("")) {
                        filter = filter.and(CoffeemachineSpecification.ltWater(0.6));
                    } else if(range.length == 2) {
                        Double water1 = Double.parseDouble(range[0]);
                        Double water2 = Double.parseDouble(range[1]);
                        filter = filter.and(CoffeemachineSpecification.betweenWater(water1, water2));
                    } else {
                        filter = filter.and(CoffeemachineSpecification.gtWater(Double.valueOf(1)));
                    }
                }
            }

            coffeemachinelist = coffeemachineRepository.findAll(filter, pageRequest);
        }

        int count = 1;
        for(Coffeemachine coffeemachine : coffeemachinelist) {
            Optional<CoffeemachinePerformance> coffeemachinePerformance = coffeemachinePerformanceRepository.findById(coffeemachine.getPcode());
            if(coffeemachinePerformance.isPresent()) {
                coffeemachineGetResList.add(CoffeemachineGetRes.of(coffeemachine, coffeemachinePerformance.get()));
            }
            System.out.println("count >> " + (count++) + " / price >> " + coffeemachine.getPrice()+ " / pressure >> " + coffeemachine.getPressure() + " / heatTime >>> " + coffeemachine.getHeatTime());
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
