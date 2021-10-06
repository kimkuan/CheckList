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
import com.ssafy.checklist.domain.main.entity.ClickCount;
import com.ssafy.checklist.domain.main.entity.Filter;
import com.ssafy.checklist.domain.main.repository.ClickCountRepository;
import com.ssafy.checklist.domain.main.repository.FilterRepository;
import com.ssafy.checklist.domain.main.repository.MainRepository;
import com.ssafy.checklist.domain.monitor.controller.response.MonitorGetRes;
import com.ssafy.checklist.domain.monitor.repository.MonitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return filterRepository.findById(category).orElse(null);
    }

    public void updateProductHit(String pcode) {
        ClickCount count = clickCountRepository.findById(Long.parseLong(pcode)).get();
        count.setCount(count.getCount()+1);
        clickCountRepository.save(count);
    }

//    public List<?> findByKeyword(String keyword) {
//
//        coffeemachineRepository
//
//
//        return null;
//    }

    public void findBestProduct() {

    }

}
