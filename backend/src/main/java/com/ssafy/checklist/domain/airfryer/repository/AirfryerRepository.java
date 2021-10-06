package com.ssafy.checklist.domain.airfryer.repository;

import com.ssafy.checklist.domain.airfryer.entity.Airfryer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirfryerRepository extends JpaRepository<Airfryer, Long> {

}
