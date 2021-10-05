package com.ssafy.checklist.domain.coffeemachine.repository;

import com.ssafy.checklist.domain.coffeemachine.entity.CoffeemachinePerformance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoffeemachinePerformanceRepository extends JpaRepository<CoffeemachinePerformance, Long> {

}
