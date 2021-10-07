package com.ssafy.checklist.domain.aircleaner.service;

import com.ssafy.checklist.domain.aircleaner.controller.response.AircleanerGetRes;
import com.ssafy.checklist.domain.aircleaner.entity.Aircleaner;
import com.ssafy.checklist.domain.aircleaner.entity.AircleanerPerformance;
import com.ssafy.checklist.domain.aircleaner.repository.AircleanerPerformanceRepository;
import com.ssafy.checklist.domain.aircleaner.repository.AircleanerRepository;
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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Service
public class AircleanerService {
    @Autowired
    AircleanerRepository aircleanerRepository;

    @Autowired
    AircleanerPerformanceRepository aircleanerPerformanceRepository;

    @Autowired
    LowPriceInfoRepository lowPriceInfoRepository;

    // pcode로 해당 상품 '정보' 반환
    public Aircleaner findAircleanerByPcode(Long pcode) {
        return aircleanerRepository.findById(pcode).orElse(null);
    }

    // pcode로 해당 상품 '성능' 반환
    public AircleanerPerformance findAircleanerPerformanceByPcode(Long pcode) {
        return aircleanerPerformanceRepository.findById(pcode).orElse(null);
    }

    // pcode로 해당 상품 '최저가 리스트' 반환
    public List<LowPriceInfo> findLowPriceByPcode(Long pcode) {
        return lowPriceInfoRepository.findAllByPcode(pcode).orElse(null);
    }

    /**
     * @Method Name : findAircleanerByFilters
     * @작성자 : 김선혜
     * @Method 설명 : 필터링 검색 결과를 페이징 처리하여 가져오는 메소드 호출.
     *               해당하는 상품들의 성능 분석 정보를 가져오는 메소드를 호출.
     *               두 메소드 호출의 결과를 AircleanerGetRes 타입으로 묶어 반환함.
     */
    public List<AircleanerGetRes> findAircleanerByFilters(Pageable pageable, Map<String, Object> map) {
        Specification<Aircleaner> filter = getMultiFilter(map);
        Page<Aircleaner> aircleanerPage = aircleanerRepository.findAll(filter, pageable);
        List<Aircleaner> aircleanerList = aircleanerPage.getContent();

        List<AircleanerGetRes> aircleanerGetResList = new ArrayList<>();
        for (Aircleaner aircleaner : aircleanerList) {
            Optional<AircleanerPerformance> aircleanerPerformance = aircleanerPerformanceRepository.findById(aircleaner.getPcode());
            aircleanerPerformance.ifPresent(performance -> aircleanerGetResList.add(AircleanerGetRes.from(aircleaner, performance)));
        }

        return aircleanerGetResList;
    }

    /**
     * @Method Name : getMultiFilter
     * @작성자 : 김선혜
     * @Method 설명 : 필터링 정보를 Map으로 가져와 쿼리를 생성하여 검색하고 결과를 반환. (복수 조건)
     */
    @SuppressWarnings({"unused", "unchecked"})
    public Specification<Aircleaner> getMultiFilter(Map<String, Object> map) {
        return new Specification<Aircleaner>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Aircleaner> root, javax.persistence.criteria.CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate p = cb.conjunction();
                List<Predicate> pList = new ArrayList<>();

                if (map.get("가격대") != null) {
                    List<String> values = (ArrayList<String>) map.get("가격대");
                    System.out.println("가격대: " + values.get(0));
                    // 전체가 들어간 경우는 필터링 필요없기 때문에 아무 조건도 넣지 않고 다음 필터로 이동
                    if(!values.contains("전체")) {
                        String[] prices = values.get(0).split("~");
                        long minPrice = Long.parseLong(prices[0]+"0000");
                        long maxPrice = Long.parseLong(prices[1]+"0000");

                        // 첫번째 조건은 and로 이어줘야 다른 필터구분과 같이 검색됨
                        p = cb.and(p, cb.between(root.get("price"), minPrice, maxPrice));
                        for(int i=1, n=values.size(); i<n; i++) {
                            String[] prices2 = values.get(i).split("~");
                            long minPrice2 = Long.parseLong(prices2[0]+"0000");
                            long maxPrice2 = Long.parseLong(prices2[1]+"0000");

                            // 같은 필터구분 내에서는 or로 이어줘야 해당하는 조건 모두 만족하는 값을 가져옴
                            p = cb.or(p, cb.between(root.get("price"), minPrice2, maxPrice2));
                        }
                    }
                }

                if(map.get("사용면적") != null) {
                    List<String> values = (ArrayList<String>) map.get("사용면적");
                    System.out.println("사용면적: " + values.get(0));

                    if(!values.contains("전체")) {
                        String[] areas = values.get(0).split("~");

                        if (areas[0].isEmpty()) areas[0]="0";
                        if (areas[1].isEmpty()) areas[1]="2100000000";
                        Double minArea = Double.parseDouble(areas[0]);
                        Double maxArea = Double.parseDouble(areas[1]);

                        p = cb.and(p, cb.between(root.get("area"), minArea, maxArea));

                        for(int i=1, n=values.size(); i<n; i++) {
                            String[] areas2 = values.get(i).split("~");

                            if (areas[0].isEmpty()) areas[0]="0";
                            if (areas[1].isEmpty()) areas[1]="2100000000";

                            Double minArea2 = Double.parseDouble(areas2[0]);
                            Double maxArea2 = Double.parseDouble(areas2[1]);

                            p = cb.or(p, cb.between(root.get("area"), minArea2, maxArea2));
                        }
                    }
                }

                if(map.get("필터 타입") != null) {
                    List<String> values = (ArrayList<String>) map.get("필터 타입");

                    if(!values.contains("전체")) {
                        p = cb.and(p, cb.like(root.get("type"), "%"+values.get(0)+"%"));
                        for(int i=1, n=values.size(); i<n; i++) {
                            p = cb.or(p, cb.like(root.get("type"), "%"+values.get(i)+"%"));
                        }
                    }
                }

                if(map.get("미세먼지필터링") != null) {
                    List<String> values = (ArrayList<String>) map.get("미세먼지필터링");

                    if(!values.contains("전체")) {
                        if (values.get(0).equals("X")) {
                            p = cb.and(p, cb.equal(root.get("dust"), "null"));
                        } else {
                            p = cb.and(p, cb.equal(root.get("dust"), "○"));
                        }
                        for(int i=1, n=values.size(); i<n; i++) {
                            if (values.get(i).equals("X")) {
                                p = cb.or(p, cb.equal(root.get("dust"), "null"));
                            } else {
                                p = cb.or(p, cb.equal(root.get("dust"), "○"));
                            }
                        }
                    }
                }
                return p;
            }
        };
    }

    /**
     * @Method Name : findCheckPick
     * @작성자 : 김선혜
     * @Method 설명 : 공기청정기 체크픽 제공
     */
    public List<AircleanerGetRes> findCheckPick() {
        List<AircleanerGetRes> aircleanerGetResList = new ArrayList<>();
        List<AircleanerPerformance> aircleanerPerformances = aircleanerPerformanceRepository.findCheckPick();
        for(AircleanerPerformance aircleanerPerformance : aircleanerPerformances) {
            Aircleaner aircleaner = aircleanerRepository.getById(aircleanerPerformance.getPcode());
            aircleanerGetResList.add(AircleanerGetRes.from(aircleaner, aircleanerPerformance));
        }
        return aircleanerGetResList;
    }

    /**
     * @Method Name : findAllByKeyword
     * @작성자 : 김선혜
     * @Method 설명 : keyword를 포함하는 공기청정기 제공
     */
    public List<AircleanerGetRes> findAllByKeyword(int page, String keyword) {
        PageRequest pageRequest = PageRequest.of(page, 30, Sort.Direction.DESC, "pcode");
        Page<Aircleaner> aircleaners = aircleanerRepository.findAllByNameContaining(keyword, pageRequest);
        long totalResultCount= aircleanerRepository.countByNameContaining(keyword); // 총 검색결과 가져오기 위함
        List<AircleanerGetRes> aircleanerGetResList = new ArrayList<>();
        int size = aircleaners.getContent().size();
        for(int i = 0; i < size; i++) {
            Aircleaner aircleaner = aircleaners.getContent().get(i);
            Optional<AircleanerPerformance> aircleanerPerformance = aircleanerPerformanceRepository.findById(aircleaner.getPcode());
            aircleanerPerformance.ifPresent(performance -> aircleanerGetResList.add(AircleanerGetRes.fromWithTotalResultCount(aircleaner, performance, totalResultCount)));
        }

        return aircleanerGetResList;
    }
}
