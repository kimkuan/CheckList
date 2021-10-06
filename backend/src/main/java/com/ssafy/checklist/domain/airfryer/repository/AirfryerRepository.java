package com.ssafy.checklist.domain.airfryer.repository;

import com.ssafy.checklist.domain.airfryer.entity.Airfryer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AirfryerRepository extends JpaRepository<Airfryer, Long> {
    Page<Airfryer> findAllByNameContaining(String keyword, Pageable page);
}
