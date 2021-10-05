package com.ssafy.checklist.domain.monitor.repository;

import com.ssafy.checklist.domain.monitor.entity.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MonitorRepository extends JpaRepository<Monitor, String> {

    Optional<Monitor> findMonitorByPcode(long pcode);
}
