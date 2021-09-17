package com.ssafy.checklist.domain.foodprocessor.repository;

import com.ssafy.checklist.domain.foodprocessor.entity.Foodprocessor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodprocessorRepository extends JpaRepository<Foodprocessor, String> {
}
