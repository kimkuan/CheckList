package com.ssafy.checklist.domain.foodprocessor.repository;

import com.ssafy.checklist.domain.foodprocessor.entity.Foodprocessor;
import com.ssafy.checklist.domain.monitor.entity.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodprocessorRepository extends JpaRepository<Foodprocessor, Long>, JpaSpecificationExecutor<Foodprocessor> {
}
