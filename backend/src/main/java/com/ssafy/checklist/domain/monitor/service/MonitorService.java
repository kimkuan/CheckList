package com.ssafy.checklist.domain.monitor.service;

import com.ssafy.checklist.domain.common.entity.LowPriceInfo;
import com.ssafy.checklist.domain.common.repository.LowPriceInfoRepository;
import com.ssafy.checklist.domain.monitor.entity.Monitor;
import com.ssafy.checklist.domain.monitor.repository.MonitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
