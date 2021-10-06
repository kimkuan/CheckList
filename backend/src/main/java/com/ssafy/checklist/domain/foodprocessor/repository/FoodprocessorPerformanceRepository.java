package com.ssafy.checklist.domain.foodprocessor.repository;

import com.ssafy.checklist.domain.foodprocessor.entity.FoodprocessorPerformance;
import com.ssafy.checklist.domain.monitor.entity.MonitorPerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodprocessorPerformanceRepository extends JpaRepository<FoodprocessorPerformance, Long> {
}
