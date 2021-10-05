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
import org.springframework.stereotype.Service;

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

    public List<CoffeemachineGetRes> findAll() {
        return null;
    }

    public Coffeemachine findCoffeemachineByPcode(Long pcode) {
        Optional<Coffeemachine> coffeemachine = coffeemachineRepository.findById(pcode);

        if(coffeemachine.isPresent()) {
            return coffeemachine.get();
        }
        return null;
    }

    public CoffeemachinePerformance findCoffeemachinePerformanceByPcode(Long pcode) {
        Optional<CoffeemachinePerformance> coffeemachinePerformance = coffeemachinePerformanceRepository.findById(pcode);

        if(coffeemachinePerformance.isPresent()) {
            return coffeemachinePerformance.get();
        }
        return null;
    }

    public List<LowPriceInfo> findLowPriceInfoByPcode(Long pcode) {
        Optional<List<LowPriceInfo>> lowPriceInfo = lowPriceInfoRepository.findByPcode(pcode);

        if(lowPriceInfo.isPresent()) {
            return lowPriceInfo.get();
        }
        return null;
    }
}
