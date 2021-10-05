package com.ssafy.checklist.domain.monitor.repository;

import com.ssafy.checklist.domain.monitor.entity.Monitor;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MonitorRepository extends PagingAndSortingRepository<Monitor, Long>, JpaSpecificationExecutor<Monitor> {

    Optional<Monitor> findMonitorByPcode(long pcode);
}
