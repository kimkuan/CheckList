package com.ssafy.checklist.domain.monitor.service;

import com.ssafy.checklist.domain.coffeemachine.controller.response.CoffeemachineGetRes;
import com.ssafy.checklist.domain.coffeemachine.entity.Coffeemachine;
import com.ssafy.checklist.domain.coffeemachine.entity.CoffeemachinePerformance;
import com.ssafy.checklist.domain.common.entity.LowPriceInfo;
import com.ssafy.checklist.domain.common.repository.LowPriceInfoRepository;
import com.ssafy.checklist.domain.monitor.controller.response.MonitorGetRes;
import com.ssafy.checklist.domain.monitor.controller.response.MonitorInfoGetRes;
import com.ssafy.checklist.domain.monitor.entity.Monitor;
import com.ssafy.checklist.domain.monitor.entity.MonitorPerformance;
import com.ssafy.checklist.domain.monitor.repository.MonitorPerformanceRepository;
import com.ssafy.checklist.domain.monitor.repository.MonitorRepository;
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
public class MonitorService {

    @Autowired
    MonitorRepository monitorRepository;

    @Autowired
    LowPriceInfoRepository lowPriceInfoRepository;

    @Autowired
    MonitorPerformanceRepository monitorPerformanceRepository;

    public Monitor findMonitorById(String pcode) {

        return monitorRepository.findMonitorByPcode(Long.parseLong(pcode)).orElse(null);
    }

    public MonitorPerformance findMonitorPerformanceById(String pcode) {
        return monitorPerformanceRepository.findById(Long.parseLong(pcode)).orElse(null);
    }

    public List<LowPriceInfo> findLowPriceById(String pcode) {

        return lowPriceInfoRepository.findAllByPcode(Long.parseLong(pcode)).orElse(null);
    }

    public List<MonitorInfoGetRes> findMonitorByFilters(Map<String, Object> map, Pageable pageRequest) {

        /**
        * @Method Name : findMonitorByFilters
        * @작성자 : 김윤주
        * @Method 설명 : 필터링 검색 결과를 페이징 처리하여 가져오는 메소드 호출.
        *               해당하는 상품들의 성능 분석 정보를 가져오는 메소드를 호출.
        *               두 메소드 호출의 결과를 MonitorInfoGetRes 타입으로 묶어 반환함.
        */

        Specification<Monitor> spec = getMultiFilter(map);

        // 필터링 검색 결과 목록
        Page<Monitor> mp = monitorRepository.findAll(spec, pageRequest);
        List<Monitor> mList = mp.getContent();

        // 성능분석 정보
        List<MonitorPerformance> mpList = new LinkedList<>();
        for(int i=0; i< mList.size(); i++) {
            mpList.add(monitorPerformanceRepository.findById(mList.get(i).getPcode()).orElse(null));
            System.out.println("가져온 성능 >>> " + mpList.get(i));
        }

        return MonitorInfoGetRes.of(mList, mpList);
    }

//    public List<MonitorPerformance> findMonitorPerformanceByPcode(long[] pList) {
//        /**
//        * @Method Name : findMonitorPerformanceByPcode
//        * @작성자 : 김윤주
//        * @Method 설명 : pcode List에 해당하는 성능분석 정보를 List로 가져와 반환함
//        */
//
//        List<MonitorPerformance> mpList = new LinkedList<>();
//
//        for(int i=0; i<pList.length; i++) {
//            mpList.add(monitorPerformanceRepository.findById(pList[i]).orElse(null));
//        }
//
//        return mpList;
//    }

    @SuppressWarnings({"unused", "unchecked"})
    public Specification<Monitor> getMultiFilter(Map<String, Object> map) {
        /**
        * @Method Name : getMultiFilter
        * @작성자 : 김윤주
        * @Method 설명 : 필터링 정보를 Map으로 가져와 쿼리를 생성하여 검색하고 결과를 반환. (복수 조건)
        */

        return new Specification<Monitor>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Monitor> root, javax.persistence.criteria.CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate p = cb.conjunction();
                List<Predicate> pList = new ArrayList<Predicate>();

                if (map.get("가격대") != null) {
                    List<String> values = (ArrayList<String>) map.get("가격대");

                    // 전체가 들어간 경우는 필터링 필요없기 때문에 아무 조건도 넣지 않고 다음 필터로 이동
                    if(!values.contains("전체")) {
                        String[] prices = values.get(0).split("~");

                        // 첫번째 조건은 and로 이어줘야 다른 필터구분과 같이 검색됨
                        if (prices[0].equals("")) {
                            long maxPrice = Long.parseLong(prices[1]+"0000");
                            p = cb.and(p, cb.lessThanOrEqualTo(root.get("price"), maxPrice));
                        } else if(prices[1].equals("")) {
                            long minPrice = Long.parseLong(prices[0]+"0000");
                            p = cb.and(p, cb.greaterThanOrEqualTo(root.get("price"), minPrice));
                        } else {
                            long maxPrice = Long.parseLong(prices[1]+"0000");
                            long minPrice = Long.parseLong(prices[0]+"0000");
                            p = cb.and(p, cb.between(root.get("price"), minPrice, maxPrice));
                        }

                        // 같은 필터구분 내에서는 or로 이어줘야 해당하는 조건 모두 만족하는 값을 가져옴
                        for(int i=1, n=values.size(); i<n; i++) {
                            String[] prices2 = values.get(i).split("~");

                            if (prices2[0].equals("")) {
                                long maxPrice2 = Long.parseLong(prices2[1]+"0000");
                                p = cb.or(p, cb.lessThanOrEqualTo(root.get("price"), maxPrice2));
                            } else if(prices2[1].equals("")) {
                                long minPrice2 = Long.parseLong(prices2[0]+"0000");
                                p = cb.or(p, cb.greaterThanOrEqualTo(root.get("price"), minPrice2));
                            } else {
                                long maxPrice2 = Long.parseLong(prices2[1]+"0000");
                                long minPrice2 = Long.parseLong(prices2[0]+"0000");
                                p = cb.or(p, cb.between(root.get("price"), minPrice2, maxPrice2));
                            }
                        }
                    }

                }

                if(map.get("화면 크기") != null) {
                    List<String> values = (ArrayList<String>) map.get("화면 크기");

                    if(!values.contains("전체")) {
                        p = cb.and(p, cb.like(root.get("size"), "%"+values.get(0)+"%"));
                        for(int i=1, n=values.size(); i<n; i++) {
                            p = cb.or(p, cb.like(root.get("size"), "%"+values.get(i)+"%"));
                        }
                    }
                }

                if(map.get("해상도") != null) {
                    List<String> values = (ArrayList<String>) map.get("해상도");

                    if(!values.contains("전체")) {
                        p = cb.and(p, cb.like(root.get("pixel"), "%"+values.get(0)+"%"));
                        for(int i=1, n=values.size(); i<n; i++) {
                            p = cb.or(p, cb.like(root.get("pixel"), "%"+values.get(i)+"%"));
                        }
                    }
                }

                if(map.get("주사율") != null) {
                    List<String> values = (ArrayList<String>) map.get("주사율");

                    if(!values.contains("전체")) {
                        p = cb.and(p, cb.equal(root.get("hz"), values.get(0).substring(0, values.get(0).length()-2)));
                        for(int i=1, n=values.size(); i<n; i++) {
                            p = cb.or(p, cb.equal(root.get("hz"), values.get(0).substring(0, values.get(0).length()-2)));
                        }
                    }
                }

                return p;
            }

        };
    }


    public List<MonitorGetRes> findCheckPick() {
        List<MonitorGetRes> monitorGetResList = new ArrayList<>();
        List<MonitorPerformance> monitorPerformances = monitorPerformanceRepository.findCheckPick();

        for(MonitorPerformance monitorPerformance : monitorPerformances) {
            Monitor monitor = monitorRepository.findMonitorByPcode(monitorPerformance.getPcode()).get();
            monitorGetResList.add(MonitorGetRes.of(monitor, monitorPerformance, null));
        }
        return monitorGetResList;
    }

    public List<MonitorGetRes> findAllByKeyword(int page, String keyword) {
        PageRequest pageRequest = PageRequest.of(page, 30, Sort.Direction.DESC, "pcode");
        Page<Monitor> monitors = monitorRepository.findAllByNameContaining(keyword, pageRequest);

        List<MonitorGetRes> monitorGetResList = new ArrayList<>();
        int size = monitors.getSize();
        for(int i = 0; i < size; i++) {
            Monitor monitor = monitors.getContent().get(i);
            Optional<MonitorPerformance> monitorPerformance = monitorPerformanceRepository.findById(monitor.getPcode());
            if(monitorPerformance.isPresent()) {
                monitorGetResList.add(MonitorGetRes.of(monitor, monitorPerformance.get(), null));
            }
        }

        return monitorGetResList;
    }
}
