package com.ssafy.checklist.domain.airfryer.repository;

import com.ssafy.checklist.domain.airfryer.entity.AirfryerPerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirfryerPerformanceRepository extends JpaRepository<AirfryerPerformance, Long> {

    @Query(
            value = "SELECT * FROM airfryer_performance "
                    + "ORDER BY (price + volume + cook + control + management + convenience) DESC LIMIT 10",
            nativeQuery = true
    )
    List<AirfryerPerformance> findCheckPick();
}
