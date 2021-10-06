package com.ssafy.checklist.domain.monitor.repository;

import com.ssafy.checklist.domain.monitor.entity.MonitorPerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitorPerformanceRepository extends JpaRepository<MonitorPerformance, Long> {

    @Query(
            value = "SELECT * FROM monitor_performance "
                    + "ORDER BY (price + size + pixel + screen_change + convenience) DESC LIMIT 10",
            nativeQuery = true
    )
    List<MonitorPerformance> findCheckPick();
}
