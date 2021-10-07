package com.ssafy.checklist.domain.coffeemachine.repository;

import com.ssafy.checklist.domain.coffeemachine.entity.CoffeemachinePerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CoffeemachinePerformanceRepository extends JpaRepository<CoffeemachinePerformance, Long> {

    @Query(
            value = "SELECT * FROM coffeemachine_performance "
                    + "ORDER BY (price + brewing + water_volume + convenience) DESC LIMIT 10",
            nativeQuery = true
    )
    List<CoffeemachinePerformance> findCheckPick();

}
