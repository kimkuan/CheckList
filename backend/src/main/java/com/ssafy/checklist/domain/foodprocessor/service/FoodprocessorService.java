package com.ssafy.checklist.domain.foodprocessor.service;

import com.ssafy.checklist.domain.foodprocessor.controller.response.FoodprocessorGetRes;
import com.ssafy.checklist.domain.foodprocessor.repository.FoodprocessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodprocessorService {

    @Autowired
    FoodprocessorRepository foodprocessorRepository;

    public List<FoodprocessorGetRes> findAllFoodprocessor() {
        return foodprocessorRepository
                .findAll()
                .stream()
                .map(FoodprocessorGetRes::of)
                .collect(Collectors.toList());
    }

    public FoodprocessorGetRes findFoodprocessorById(Long pcode) {
        return foodprocessorRepository.findById(pcode).map(FoodprocessorGetRes::of).orElse(null);
    }
}
