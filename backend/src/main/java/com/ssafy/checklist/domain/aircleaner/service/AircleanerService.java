package com.ssafy.checklist.domain.aircleaner.service;

import com.ssafy.checklist.domain.aircleaner.controller.response.AircleanerGetRes;
import com.ssafy.checklist.domain.aircleaner.entity.Aircleaner;
import com.ssafy.checklist.domain.aircleaner.repository.AircleanerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.ssafy.checklist.domain.aircleaner.controller.response.AircleanerGetRes.from;

@Service
public class AircleanerService {
    @Autowired
    AircleanerRepository aircleanerRepository;

    /**
     * @Method Name : findByFilter
     * @작성자 : 김선혜
     * @Method 설명 : 주어진 페이지번호, 필터 조건에 맞는 공기청정기 목록 제공
     */
    public Page<Aircleaner> findByFilter(Pageable pageable) {
        return aircleanerRepository.findAll(pageable);
    }

    /**
     * @Method Name : findById
     * @작성자 : 김선혜
     * @Method 설명 : pcode에 맞는 공기청정기 제공
     */
    public AircleanerGetRes findById(Long pcode) {
        Optional<Aircleaner> optionalAircleaner = aircleanerRepository.findById(pcode);
        return optionalAircleaner.map(AircleanerGetRes::from).orElse(null);
    }
}
