package com.ssafy.checklist.domain.foodprocessor.service;

import com.ssafy.checklist.domain.common.entity.LowPriceInfo;
import com.ssafy.checklist.domain.common.repository.LowPriceInfoRepository;
import com.ssafy.checklist.domain.foodprocessor.controller.response.FoodprocessorGetRes;
import com.ssafy.checklist.domain.foodprocessor.entity.Foodprocessor;
import com.ssafy.checklist.domain.foodprocessor.repository.FoodprocessorRepository;
import com.ssafy.checklist.domain.monitor.entity.Monitor;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodprocessorService {

    @Autowired
    FoodprocessorRepository foodprocessorRepository;

    @Autowired
    LowPriceInfoRepository lowPriceInfoRepository;

//    public List<FoodprocessorGetRes> findAllFoodprocessor() {
//        return foodprocessorRepository
//                .findAll()
//                .stream()
//                .map(FoodprocessorGetRes::of)
//                .collect(Collectors.toList());
//    }

    public FoodprocessorGetRes findFoodprocessorById(Long pcode) {
        List<LowPriceInfo> lowPriceInfolist = lowPriceInfoRepository.findAllByPcode(pcode).orElse(null);
        return foodprocessorRepository.findById(pcode)
                .map(foodprocessor -> FoodprocessorGetRes.of(foodprocessor, lowPriceInfolist))
                .orElse(null);
    }

    public Page<Foodprocessor> findFoodprocessorByFilters(Map<String, Object> map, Pageable pageRequest) {
        Specification<Foodprocessor> spec = getMultiFilter(map);

        return foodprocessorRepository.findAll(spec, pageRequest);
    }

    @SuppressWarnings({"unused", "unchecked"})
    public Specification<Foodprocessor> getMultiFilter(Map<String, Object> map) {
        return new Specification<Foodprocessor>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Foodprocessor> root, javax.persistence.criteria.CriteriaQuery<?> query, CriteriaBuilder cb) {
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

                if(map.get("처리방식") != null) {
                    List<String> values = (ArrayList<String>) map.get("처리방식");

                    if(!values.contains("전체")) {
                        p = cb.and(p, cb.equal(root.get("processType"), values.get(0)));
                        for(int i=1, n=values.size(); i<n; i++) {
                            p = cb.or(p, cb.equal(root.get("processType"), values.get(i)));
                        }
                    }
                }

                if(map.get("감소율") != null) {
                    List<String> values = (ArrayList<String>) map.get("감소율");

                    if(!values.contains("전체")) {
                        String[] decreases = values.get(0).split("~");
                        long minDecrease = Long.parseLong(decreases[0]);
                        long maxDecrease = Long.parseLong(decreases[1]);

                        // 첫번째 조건은 and로 이어줘야 다른 필터구분과 같이 검색됨
                        p = cb.and(p, cb.between(root.get("decrease"), minDecrease, maxDecrease));
                        for(int i=1, n=values.size(); i<n; i++) {
                            String[] decreases2 = values.get(i).split("~");
                            long minDecrease2 = Long.parseLong(decreases2[0]);
                            long maxDecrease2 = Long.parseLong(decreases2[1]);

                            // 첫번째 조건은 and로 이어줘야 다른 필터구분과 같이 검색됨
                            p = cb.and(p, cb.between(root.get("decrease"), minDecrease2, maxDecrease2));
                        }
                    }
                }

                if(map.get("소음") != null) {
                    List<String> values = (ArrayList<String>) map.get("소음");

                    if(!values.contains("전체")) {
                        String[] decibels = values.get(0).split("~");
                        long minDecibel = Long.parseLong(decibels[0]);
                        long maxDecibel = Long.parseLong(decibels[1]);

                        // 첫번째 조건은 and로 이어줘야 다른 필터구분과 같이 검색됨
                        p = cb.and(p, cb.between(root.get("sound"), minDecibel, maxDecibel));
                        for(int i=1, n=values.size(); i<n; i++) {
                            String[] decibels2 = values.get(i).split("~");
                            long minDecibel2 = Long.parseLong(decibels2[0]);
                            long maxDecibel2 = Long.parseLong(decibels2[1]);

                            // 첫번째 조건은 and로 이어줘야 다른 필터구분과 같이 검색됨
                            p = cb.and(p, cb.between(root.get("sound"), minDecibel2, maxDecibel2));
                        }
                    }
                }

                return p;
            }

        };
    }
}
