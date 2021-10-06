package com.ssafy.checklist.domain.main.service;

import com.ssafy.checklist.domain.aircleaner.entity.Aircleaner;
import com.ssafy.checklist.domain.aircleaner.repository.AircleanerRepository;
import com.ssafy.checklist.domain.airfryer.entity.Airfryer;
import com.ssafy.checklist.domain.airfryer.repository.AirfryerRepository;
import com.ssafy.checklist.domain.coffeemachine.entity.Coffeemachine;
import com.ssafy.checklist.domain.coffeemachine.repository.CoffeemachineRepository;
import com.ssafy.checklist.domain.foodprocessor.entity.Foodprocessor;
import com.ssafy.checklist.domain.foodprocessor.repository.FoodprocessorRepository;
import com.ssafy.checklist.domain.main.controller.response.FilterGetRes;
import com.ssafy.checklist.domain.main.controller.response.ProductGetRes;
import com.ssafy.checklist.domain.main.entity.ClickCount;
import com.ssafy.checklist.domain.main.entity.Filter;
import com.ssafy.checklist.domain.main.entity.Product;
import com.ssafy.checklist.domain.main.repository.ClickCountRepository;
import com.ssafy.checklist.domain.main.repository.FilterRepository;
import com.ssafy.checklist.domain.main.repository.MainRepository;
import com.ssafy.checklist.domain.monitor.controller.response.MonitorGetRes;
import com.ssafy.checklist.domain.monitor.repository.MonitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.LinkedList;
import java.util.List;

@Service
public class MainService {

    @Autowired
    MainRepository mainRepository;

    @Autowired
    FilterRepository filterRepository;

    @Autowired
    ClickCountRepository clickCountRepository;

    @Autowired
    CoffeemachineRepository coffeemachineRepository;

    @Autowired
    AircleanerRepository aircleanerRepository;

    @Autowired
    AirfryerRepository airfryerRepository;

    @Autowired
    MonitorRepository monitorRepository;

    @Autowired
    FoodprocessorRepository foodprocessorRepository;

    public Filter findFilterByCategory(String category) {

        /**
        * @Method Name : findFilterByCategory
        * @작성자 : 김윤주
        * @Method 설명 : 카테고리명을 통해 filter 구분 항목을 가져옴.
        */

        return filterRepository.findById(category).orElse(null);
    }

    public void updateProductHit(String pcode) {

        /**
        * @Method Name : updateProductHit
        * @작성자 : 김윤주
        * @Method 설명 : click_count 테이블에 해당 pcode의 count횟수를 가져와 +1하여 저장.
        */

        ClickCount count = clickCountRepository.findById(Long.parseLong(pcode)).get();
        count.setCount(count.getCount()+1);
        clickCountRepository.save(count);
    }

    public List<ProductGetRes> findBestProduct() {

        /**
        * @Method Name : findBestProduct
        * @작성자 : 김윤주
        * @Method 설명 : clickcount 테이블에서 조회수 내림차순으로 3개 가져옴.
        *               가져온 3개의 category에 해당하는 테이블에서 pcode로 상품 데이터 가져옴.
        */

        // clickcount count desc 정렬하여 3개 가져옴
        PageRequest pg = PageRequest.of(0, 3, Sort.by("count"));
        List<ClickCount> clickCounts = clickCountRepository.findAll(pg).getContent();

        // ProductGetRes 형태로 반환
        List<ProductGetRes> products = new LinkedList<>();

        // pcode로 해당 상품 정보 가져와서 products 리스트에 add
        for(int i=0; i<clickCounts.size(); i++) {
            ClickCount c = clickCounts.get(i);

            switch (c.getCategory()) {
                case "Aircleaner":
                    products.add(ProductGetRes.of(aircleanerRepository.findById(c.getPcode()).orElse(null)));
                    break;
                case "AirFryer":
                    products.add(ProductGetRes.of(airfryerRepository.findById(c.getPcode()).orElse(null)));
                    break;
                case "CoffeeMachine":
                    products.add(ProductGetRes.of(coffeemachineRepository.findById(c.getPcode()).orElse(null)));
                    break;
                case "FoodProcessor":
                    products.add(ProductGetRes.of(foodprocessorRepository.findById(c.getPcode()).orElse(null)));
                    break;
                case "Monitor":
                    products.add(ProductGetRes.of(monitorRepository.findById(c.getPcode()).orElse(null)));
                    break;
                default:
                    break;
            }

        }

        return products;
    }

}
