package com.ssafy.checklist.domain.monitor.service;

import com.ssafy.checklist.domain.common.entity.LowPriceInfo;
import com.ssafy.checklist.domain.common.repository.LowPriceInfoRepository;
import com.ssafy.checklist.domain.monitor.entity.Monitor;
import com.ssafy.checklist.domain.monitor.repository.MonitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MonitorService {

    @Autowired
    MonitorRepository monitorRepository;

    @Autowired
    LowPriceInfoRepository lowPriceInfoRepository;

    public Monitor findMonitorById(String pcode) {

        return monitorRepository.findMonitorByPcode(Long.parseLong(pcode)).orElse(null);
    }

    public List<LowPriceInfo> findLowPriceById(String pcode) {

        return lowPriceInfoRepository.findAllByPcode(Long.parseLong(pcode)).orElse(null);
    }

    public Page<Monitor> findMonitorByFilters(Map<String, Object> map, Pageable pageRequest) {

        Specification<Monitor> spec = getMultiFilter(map);

        return monitorRepository.findAll(spec, pageRequest);
    }

    @SuppressWarnings({"unused", "unchecked"})
    public Specification<Monitor> getMultiFilter(Map<String, Object> map) {
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


}
