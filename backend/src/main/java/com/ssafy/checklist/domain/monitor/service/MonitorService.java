package com.ssafy.checklist.domain.monitor.service;

import com.ssafy.checklist.domain.monitor.controller.response.MoniterGetRes;
import com.ssafy.checklist.domain.monitor.repository.MoniterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoniterService {

    @Autowired
    MoniterRepository moniterRepository;

    public MoniterGetRes findMonitorById(String pcode) {

        MoniterGetRes m = new MoniterGetRes();
        m = moniterRepository.findMoniterByPcode(pcode).orElse(null);

        return m;
    }
}
