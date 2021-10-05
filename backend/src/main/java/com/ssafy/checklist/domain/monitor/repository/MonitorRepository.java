package com.ssafy.checklist.domain.monitor.repository;

import com.ssafy.checklist.domain.monitor.controller.response.MoniterGetRes;
import com.ssafy.checklist.domain.monitor.entity.Moniter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MoniterRepository extends JpaRepository<Moniter, String> {

    Optional<MoniterGetRes> findMoniterByPcode(String pcode);
}
