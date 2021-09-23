package com.ssafy.checklist.domain.airfryer.service;

import com.ssafy.checklist.domain.airfryer.controller.response.AirfryerGetRes;
import com.ssafy.checklist.domain.airfryer.entity.Airfryer;
import com.ssafy.checklist.domain.airfryer.repository.AirfryerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirfryerService {
    @Autowired
    AirfryerRepository airfryerRepository;

    public List<Airfryer> findAll() {
        return null;
    }

    public AirfryerGetRes findById(String id) {

        return null;
    }
}
