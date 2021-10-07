package com.ssafy.checklist.domain.coffeemachine.service;

import com.ssafy.checklist.domain.coffeemachine.controller.response.CoffeemachineGetRes;
import com.ssafy.checklist.domain.coffeemachine.entity.Coffeemachine;
import com.ssafy.checklist.domain.coffeemachine.entity.CoffeemachinePerformance;
import com.ssafy.checklist.domain.coffeemachine.repository.CoffeemachinePerformanceRepository;
import com.ssafy.checklist.domain.coffeemachine.repository.CoffeemachineRepository;
import com.ssafy.checklist.domain.common.entity.LowPriceInfo;
import com.ssafy.checklist.domain.common.repository.LowPriceInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    public List<CoffeemachineGetRes> findCoffeemachineListByFilter(Pageable pageable, Map<String, Object> map) {

        Page<Coffeemachine> coffeemachinelist = null;
        List<CoffeemachineGetRes> coffeemachineGetResList = new ArrayList<>();
        Specification<Coffeemachine> filter = getMultiFilter(map);

        coffeemachinelist = coffeemachineRepository.findAll(filter, pageable);

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

    public Specification<Coffeemachine> getMultiFilter(Map<String, Object> map) {
        return new Specification<Coffeemachine>() {
            @Override
            public Predicate toPredicate(Root<Coffeemachine> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate p = criteriaBuilder.conjunction();
                List<Predicate> predicateList = new ArrayList<>();

                if(map.get("가격대") != null) {
                    List<String> values = (ArrayList<String>) map.get("가격대");

                    if(!values.contains("전체")) {
                        String priceZero = values.get(0);
                        priceZero = priceZero.replace("만원", "");
                        String[] rangeZero = priceZero.split("~");

                        System.out.println("priceZero >>>> " + priceZero);
                        if(rangeZero[0].equals("")) {
                            System.out.println("가격 100000 이하");
                            p = criteriaBuilder.and(p, criteriaBuilder.lessThan(root.get("price"), Long.valueOf(100000)));
                        } else if(rangeZero.length == 2) {
                            long price1 = Long.parseLong(rangeZero[0]) * 10000;
                            long price2 = Long.parseLong(rangeZero[1]) * 10000;
                            System.out.println("가격 " + price1 + " 이상 " + price2 + " 이하");
                            p = criteriaBuilder.and(p, criteriaBuilder.between(root.get("price"), price1, price2));
                        } else {
                            System.out.println("가격 300000 이상");
                            p = criteriaBuilder.and(p, criteriaBuilder.greaterThan(root.get("price"), Long.valueOf(300000)));
                        }
                        for(int i = 1; i < values.size(); i++) {
                            String price = values.get(i);
                            System.out.println(price);
                            price = price.replace("만원","");      // L 붙은거 제거
                            String[] range = price.split("~");

                            System.out.println("가격정보 ???");
                            for (String r : range) {
                                System.out.println(r);
                            }
                            if(range[0].equals("")) {
                                p = criteriaBuilder.or(p, criteriaBuilder.lessThan(root.get("price"), Long.valueOf(100000)));
                            } else if(range.length == 2) {
                                long price1 = Long.parseLong(range[0]) * 10000;
                                long price2 = Long.parseLong(range[1]) * 10000;
                                p = criteriaBuilder.or(p, criteriaBuilder.between(root.get("price"), price1, price2));
                            } else {
                                p = criteriaBuilder.or(p, criteriaBuilder.greaterThan(root.get("price"), Long.valueOf(300000)));
                            }
                        }
                    }
                }

                if(map.get("펌프압력") != null) {
                    List<String> values = (ArrayList<String>) map.get("펌프압력");

                    if(!values.contains("전체")) {
                        p = criteriaBuilder.and(p, criteriaBuilder.equal(root.get("pressure"), values.get(0)));
                        for(int i = 1; i < values.size(); i++) {
                            p = criteriaBuilder.or(p, criteriaBuilder.equal(root.get("pressure"), values.get(i)));
                        }
                    }
                }

                if(map.get("예열시간") != null) {
                    List<String> values = (ArrayList<String>) map.get("예열시간");
                    if(!values.contains("전체")) {
                        int heatTime = Integer.parseInt(values.get(0).substring(1, 3));
                        System.out.println("예열시간 >> " + heatTime);
                        p = criteriaBuilder.and(p, criteriaBuilder.between(root.get("heatTime"), 3, heatTime));

                        for(int i = 1; i < values.size(); i++) {
                            int heat = Integer.parseInt(values.get(i).substring(1,3));
                            p = criteriaBuilder.or(p, criteriaBuilder.between(root.get("heatTime"), 3, heat));
                        }

                    }
                }

                if(map.get("물통용량") != null) {
                    List<String> values = (ArrayList<String>) map.get("물통용량");
                    if(!values.contains("전체")) {
                        String waterZero = values.get(0);
                        waterZero = waterZero.replace("L", "");
                        String[] rangeZero = waterZero.split("~");

                        System.out.println("waterZero >>>> " + waterZero);
                        if(rangeZero[0].equals("")) {
                            p = criteriaBuilder.and(p, criteriaBuilder.lessThanOrEqualTo(root.get("waterVolume"), Double.valueOf(0.6)));
                        } else if(rangeZero.length == 2) {
                            Double water1 = Double.parseDouble(rangeZero[0]);
                            Double water2 = Double.parseDouble(rangeZero[1]);
                            p = criteriaBuilder.and(p, criteriaBuilder.between(root.get("waterVolume"), water1, water2));
                        } else {
                            p = criteriaBuilder.and(p, criteriaBuilder.greaterThanOrEqualTo(root.get("waterVolume"), Double.valueOf(1)));
                        }
                        for(int i = 1; i < values.size(); i++) {
                            String water = values.get(i);
                            System.out.println(water);
                            water = water.replace("L","");      // L 붙은거 제거
                            String[] range = water.split("~");

                            if(range[0].equals("")) {
                                p = criteriaBuilder.and(p, criteriaBuilder.lessThanOrEqualTo(root.get("waterVolume"), Double.valueOf(0.6)));
                            } else if(range.length == 2) {
                                Double water1 = Double.parseDouble(range[0]);
                                Double water2 = Double.parseDouble(range[1]);
                                p = criteriaBuilder.and(p, criteriaBuilder.between(root.get("waterVolume"), water1, water2));
                            } else {
                                p = criteriaBuilder.and(p, criteriaBuilder.greaterThanOrEqualTo(root.get("waterVolume"), Double.valueOf(1)));
                            }
                        }
                    }
                }
                return p;
            }
        };
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

    /**
     * @Method Name : findCheckPick
     * @작성자 : 이영주
     * @Method 설명 : 커피머신 체크픽 제공
     */
    public List<CoffeemachineGetRes> findCheckPick() {
        List<CoffeemachineGetRes> coffeemachineGetResList = new ArrayList<>();
        List<CoffeemachinePerformance> coffeemachinePerformances = coffeemachinePerformanceRepository.findCheckPick();
        for(CoffeemachinePerformance coffeemachinePerformance : coffeemachinePerformances) {
            Coffeemachine coffeemachine = coffeemachineRepository.getById(coffeemachinePerformance.getPcode());
            coffeemachineGetResList.add(CoffeemachineGetRes.of(coffeemachine, coffeemachinePerformance));
        }
        return coffeemachineGetResList;
    }

    /**
     * @Method Name : findAllByKeyword
     * @작성자 : 이영주
     * @Method 설명 : keyword를 포함하는 커피머신 제공
     */
    public List<CoffeemachineGetRes> findAllByKeyword(int page, String keyword) {
        PageRequest pageRequest = PageRequest.of(page, 30, Sort.Direction.DESC, "pcode");
        Page<Coffeemachine> coffeemachines = coffeemachineRepository.findAllByNameContaining(keyword, pageRequest);
        long totalResultCount= coffeemachineRepository.countByNameContaining(keyword); // 총 검색결과 가져오기 위함
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> coutn : "+totalResultCount);
        List<CoffeemachineGetRes> coffeemachineGetResList = new ArrayList<>();
        int size = coffeemachines.getContent().size();
        for(int i = 0; i < size; i++) {
            Coffeemachine coffeemachine = coffeemachines.getContent().get(i);
            Optional<CoffeemachinePerformance> coffeemachinePerformance = coffeemachinePerformanceRepository.findById(coffeemachine.getPcode());
            if(coffeemachinePerformance.isPresent()) {
                coffeemachineGetResList.add(CoffeemachineGetRes.ofWithTotalResultCount(coffeemachine, coffeemachinePerformance.get(), totalResultCount));
            }
        }

        return coffeemachineGetResList;
    }
}
