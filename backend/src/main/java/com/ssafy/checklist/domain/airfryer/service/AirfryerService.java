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
public class AirfryerService {
    @Autowired
    AirfryerRepository airfryerRepository;

    @Autowired
    AirfryerPerformanceRepository airfryerPerformanceRepository;

    @Autowired
    LowPriceInfoRepository lowPriceInfoRepository;

    public List<AirfryerGetRes> findAirfryerListByFilter(Pageable pageable, Map<String, Object> filters) {

        Page<Airfryer> airfryerList = null;
        List<AirfryerGetRes> airfryerGetResList = new ArrayList<>();
        Specification<Airfryer> filter = getMultiFilter(filters);

        airfryerList = airfryerRepository.findAll(filter, pageable);

        for(Airfryer airfryer: airfryerList) {
            Optional<AirfryerPerformance> airfryerPerformance = airfryerPerformanceRepository.findById(airfryer.getPcode());
            if(airfryerPerformance.isPresent()) {
                airfryerGetResList.add(AirfryerGetRes.of(airfryer, airfryerPerformance.get()));
            }
        }

        return airfryerGetResList;
    }

    private Specification<Airfryer> getMultiFilter(Map<String, Object> filters) {
        return new Specification<Airfryer>() {
            @Override
            public Predicate toPredicate(Root<Airfryer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate p = cb.conjunction();
                List<Predicate> pList = new ArrayList<Predicate>();

                // 가격대 필터링
                if(filters.get("가격대") != null){
                    List<String> values = (ArrayList<String>) filters.get("가격대");

                    // 전체가 들어간 경우는 필터링 필요없기 때문에 아무 조건도 넣지 않고 다음 필터로 이동
                    if(!values.equals("전체")) {
                        String pricezero = values.get(0).replace("만원", "");
                        String[] prices = pricezero.split("~");

                        // 첫번째 조건은 and로 이어줘야 다른 필터구분과 같이 검색됨
                        if (prices[0].equals("")) { // ~5만원
                            long maxPrice = Long.parseLong(prices[1]+"0000");
                            p = cb.and(p, cb.lessThanOrEqualTo(root.get("price"), maxPrice));
                        } else if(prices.length == 2) { // 20만원~
                            long maxPrice = Long.parseLong(prices[1]+"0000");
                            long minPrice = Long.parseLong(prices[0]+"0000");
                            p = cb.and(p, cb.between(root.get("price"), minPrice, maxPrice));
                        } else { // 5~10만원
                            long minPrice = Long.parseLong(prices[0]+"0000");
                            p = cb.and(p, cb.greaterThanOrEqualTo(root.get("price"), minPrice));
                        }

                        // 같은 필터구분 내에서는 or로 이어줘야 해당하는 조건 모두 만족하는 값을 가져옴
                        for(int i=1, n=values.size(); i<n; i++) {
                            String pricezero2 = values.get(0).replace("만원", "");
                            String[] prices2 = pricezero2.split("~");

                            if (prices2[0].equals("")) {
                                long maxPrice2 = Long.parseLong(prices2[1] + "0000");
                                p = cb.or(p, cb.lessThanOrEqualTo(root.get("price"), maxPrice2));
                            } else if(prices2.length == 2) { // 20만원~
                                long maxPrice2 = Long.parseLong(prices2[1] + "0000");
                                long minPrice2 = Long.parseLong(prices2[0] + "0000");
                                p = cb.or(p, cb.between(root.get("price"), minPrice2, maxPrice2));
                            } else {
                                long minPrice2 = Long.parseLong(prices2[0] + "0000");
                                p = cb.or(p, cb.greaterThanOrEqualTo(root.get("price"), minPrice2));
                            }
                        }
                    }
                }

                // 형태 필터링
                if(filters.get("형태") != null) {
                    List<String> values = (ArrayList<String>) filters.get("형태");
                    if(!values.contains("전체")) {
                        String type = values.get(0);
                        p = cb.and(p, cb.equal(root.get("type"),  type));

                        for(int i = 1; i < values.size(); i++) {
                            String type2 = values.get(i);
                            p = cb.or(p, cb.equal(root.get("type"),  type2));
                        }
                    }
                }

                // 용량 필터링
                if(filters.get("용량") != null){
                    List<String> values = (ArrayList<String>) filters.get("용량");

                    // 전체가 들어간 경우는 필터링 필요없기 때문에 아무 조건도 넣지 않고 다음 필터로 이동
                    if(!values.equals("전체")) {
                        String volumezero = values.get(0).replace("L", "");
                        String[] volumes = volumezero.split("~");

                        // 첫번째 조건은 and로 이어줘야 다른 필터구분과 같이 검색됨
                        if (volumes[0].equals("")) { // ~5L
                            double maxVolume = Double.parseDouble(volumes[1]);
                            p = cb.and(p, cb.lessThanOrEqualTo(root.get("volume"), maxVolume));
                        } else if(volumes.length == 2) { // 20L~
                            double maxVolume = Double.parseDouble(volumes[1]);
                            double minVolume = Double.parseDouble(volumes[0]);
                            p = cb.and(p, cb.between(root.get("volume"), minVolume, maxVolume));
                        } else { // 5~10L
                            double minVolume = Double.parseDouble(volumes[0]);
                            p = cb.and(p, cb.greaterThanOrEqualTo(root.get("volume"), minVolume));
                        }

                        // 같은 필터구분 내에서는 or로 이어줘야 해당하는 조건 모두 만족하는 값을 가져옴
                        for(int i=1, n=values.size(); i<n; i++) {
                            String volumezero2 = values.get(0).replace("L", "");
                            String[] volumes2 = volumezero2.split("~");

                            if (volumes2[0].equals("")) { // ~5L
                                double maxVolume = Double.parseDouble(volumes2[1]);
                                p = cb.or(p, cb.lessThanOrEqualTo(root.get("volume"), maxVolume));
                            } else if(volumes2.length == 2) { // 20L~
                                double maxVolume = Double.parseDouble(volumes2[1]);
                                double minVolume = Double.parseDouble(volumes2[0]);
                                p = cb.or(p, cb.between(root.get("volume"), minVolume, maxVolume));
                            } else { // 5~10L
                                double minVolume = Double.parseDouble(volumes2[0]);
                                p = cb.or(p, cb.greaterThanOrEqualTo(root.get("volume"), minVolume));
                            }
                        }
                    }
                }

                // 소비전력 필터링
                if(filters.get("소비전력") != null){
                    List<String> values = (ArrayList<String>) filters.get("소비전력");

                    // 전체가 들어간 경우는 필터링 필요없기 때문에 아무 조건도 넣지 않고 다음 필터로 이동
                    if(!values.equals("전체")) {
                        String powerzero = values.get(0).replace("W", "");
                        String[] powers = powerzero.split("~");

                        // 첫번째 조건은 and로 이어줘야 다른 필터구분과 같이 검색됨
                        if (powers[0].equals("")) { // ~5만원
                            int maxPower = Integer.parseInt(powers[1]);
                            p = cb.and(p, cb.lessThanOrEqualTo(root.get("power"), maxPower));
                        } else if(powers.length == 2) { // 20만원~
                            int maxPower = Integer.parseInt(powers[1]);
                            int minPower = Integer.parseInt(powers[0]);
                            p = cb.and(p, cb.between(root.get("power"), minPower, maxPower));
                        } else { // 5~10만원
                            int minPower = Integer.parseInt(powers[0]);
                            p = cb.and(p, cb.greaterThanOrEqualTo(root.get("power"), minPower));
                        }

                        // 같은 필터구분 내에서는 or로 이어줘야 해당하는 조건 모두 만족하는 값을 가져옴
                        for(int i=1, n=values.size(); i<n; i++) {
                            String powerzero2 = values.get(0).replace("W", "");
                            String[] powers2 = powerzero2.split("~");

                            if (powers2[0].equals("")) { // ~1000W
                                int maxPower = Integer.parseInt(powers2[1]);
                                p = cb.and(p, cb.lessThanOrEqualTo(root.get("power"), maxPower));
                            } else if(powers2.length == 2) { // 20만원~
                                int maxPower = Integer.parseInt(powers2[1]);
                                int minPower = Integer.parseInt(powers2[0]);
                                p = cb.and(p, cb.between(root.get("power"), minPower, maxPower));
                            } else { // 2000W~
                                int minPower = Integer.parseInt(powers2[0]);
                                p = cb.and(p, cb.greaterThanOrEqualTo(root.get("power"), minPower));
                            }
                        }
                    }
                }
                return p;
            }
        };
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
        long totalResultCount= airfryerRepository.countByNameContaining(keyword); // 총 검색결과 가져오기 위함
        List<AirfryerGetRes> airfryerGetResList = new ArrayList<>();
        int size = airfryers.getContent().size();
        for(int i = 0; i < size; i++) {
            Airfryer airfryer = airfryers.getContent().get(i);
            Optional<AirfryerPerformance> airfryerPerformance = airfryerPerformanceRepository.findById(airfryer.getPcode());
            if(airfryerPerformance.isPresent()) {
                airfryerGetResList.add(AirfryerGetRes.ofWithTotalResultCount(airfryer, airfryerPerformance.get(), totalResultCount));
            }
        }

        return airfryerGetResList;
    }

}
