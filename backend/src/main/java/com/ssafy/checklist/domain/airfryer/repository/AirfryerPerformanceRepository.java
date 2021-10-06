package com.ssafy.checklist.domain.airfryer.repository;

import com.ssafy.checklist.domain.airfryer.entity.AirfryerPerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirfryerPerformanceRepository extends JpaRepository<AirfryerPerformance, Long> {

}
