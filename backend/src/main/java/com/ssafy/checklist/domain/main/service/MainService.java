package com.ssafy.checklist.domain.main.service;

import com.ssafy.checklist.domain.main.repository.MainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    @Autowired
    MainRepository mainRepository;
}
