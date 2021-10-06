package com.ssafy.checklist.domain.main.repository;

import com.ssafy.checklist.domain.main.entity.ClickCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClickCountRepository extends JpaRepository<ClickCount, Long> {

    Page<ClickCount> findAll(Pageable page);
}
