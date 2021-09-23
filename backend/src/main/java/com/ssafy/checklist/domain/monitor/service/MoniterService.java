package com.ssafy.checklist.domain.monitor.service;

import com.ssafy.checklist.domain.monitor.entity.Moniter;
import com.ssafy.checklist.domain.monitor.repository.MoniterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoniterService {

    @Autowired
    MoniterRepository moniterRepository;
}
