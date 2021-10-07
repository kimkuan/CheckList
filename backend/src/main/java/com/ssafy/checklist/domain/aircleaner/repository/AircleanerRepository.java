package com.ssafy.checklist.domain.aircleaner.repository;

import com.ssafy.checklist.domain.aircleaner.entity.Aircleaner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AircleanerRepository extends JpaRepository<Aircleaner, Long>, JpaSpecificationExecutor<Aircleaner> {
    Page<Aircleaner> findAllByNameContaining(String keyword, Pageable page);
    long countByNameContaining(String keyword);
}
