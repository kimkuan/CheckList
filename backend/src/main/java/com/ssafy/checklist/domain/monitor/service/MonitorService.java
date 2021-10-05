package com.ssafy.checklist.domain.monitor.service;

import com.ssafy.checklist.domain.monitor.entity.Monitor;
import com.ssafy.checklist.domain.monitor.repository.MonitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonitorService {

    @Autowired
    MonitorRepository monitorRepository;

    public Monitor findMonitorById(String pcode) {

        return monitorRepository.findMonitorByPcode(Long.parseLong(pcode)).orElse(null);
    }

}
