package com.ssafy.checklist.domain.coffeemachine.service;

import com.ssafy.checklist.domain.coffeemachine.controller.response.CoffeemachineGetRes;
import com.ssafy.checklist.domain.coffeemachine.entity.Coffeemachine;
import com.ssafy.checklist.domain.coffeemachine.repository.CoffeemachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeemachineService {
    @Autowired
    CoffeemachineRepository coffeemachineRepository;

    public List<Coffeemachine> findAll() {
        return null;
    }

    public CoffeemachineGetRes findById(String id) {

        return null;
    }
}
