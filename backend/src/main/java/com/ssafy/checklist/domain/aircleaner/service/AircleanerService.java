package com.ssafy.checklist.domain.aircleaner.service;

import com.ssafy.checklist.domain.aircleaner.controller.response.AircleanerGetRes;
import com.ssafy.checklist.domain.aircleaner.entity.Aircleaner;
import com.ssafy.checklist.domain.aircleaner.repository.AircleanerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AircleanerService {
    @Autowired
    AircleanerRepository aircleanerRepository;

    public List<AircleanerGetRes> findAll() {
        List<AircleanerGetRes> response = new ArrayList<>();
        return aircleanerRepository.findAll().stream()
                .map(AircleanerGetRes::from)
                .collect(Collectors.toList());
    }

    public AircleanerGetRes findById(String id) {

        return null;
    }
}
