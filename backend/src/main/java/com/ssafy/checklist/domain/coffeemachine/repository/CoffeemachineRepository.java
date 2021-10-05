package com.ssafy.checklist.domain.coffeemachine.repository;

import com.ssafy.checklist.domain.coffeemachine.entity.Coffeemachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeemachineRepository extends JpaRepository<Coffeemachine, Long> {
}
