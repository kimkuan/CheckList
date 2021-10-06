package com.ssafy.checklist.domain.aircleaner.repository;

import com.ssafy.checklist.domain.aircleaner.entity.AircleanerPerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AircleanerPerformanceRepository extends JpaRepository<AircleanerPerformance, Long> {
}
