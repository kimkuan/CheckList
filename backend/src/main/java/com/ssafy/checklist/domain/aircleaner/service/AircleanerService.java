package com.ssafy.checklist.domain.aircleaner.service;

import com.ssafy.checklist.domain.aircleaner.controller.response.AircleanerGetRes;
import com.ssafy.checklist.domain.aircleaner.entity.Aircleaner;
import com.ssafy.checklist.domain.aircleaner.repository.AircleanerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AircleanerService {
    @Autowired
    AircleanerRepository aircleanerRepository;

//    @Override
    public List<Aircleaner> findAll() {
//        return aircleanerRepository.findAll().stream()
//                .map(aircleaner -> aircleaner.c()).collect(Collectors.toList());
        return null;
    }

//    @Override
    public AircleanerGetRes findById(String id) {
        Optional<Aircleaner> optionalAircleaner = aircleanerRepository.findById(id);
//        if(optionalAircleaner.isPresent()) {
//            return optionalAircleaner.get().toAircleanerRes();
//        } else {
//            return null;
//        }
        return null;
    }
}
