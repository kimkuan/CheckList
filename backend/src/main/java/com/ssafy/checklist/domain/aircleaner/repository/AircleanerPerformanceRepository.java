package com.ssafy.checklist.domain.aircleaner.repository;

import com.ssafy.checklist.domain.aircleaner.entity.AircleanerPerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AircleanerPerformanceRepository extends JpaRepository<AircleanerPerformance, Long> {
    @Query(
            value = "SELECT * FROM aircleaner_performance "
                    + "ORDER BY (price + area + sensor + convenience) DESC LIMIT 10",
            nativeQuery = true
    )
    List<AircleanerPerformance> findCheckPick();
}
