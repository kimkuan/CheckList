package com.ssafy.checklist.domain.main.repository;

import com.ssafy.checklist.domain.main.entity.ClickCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClickCountRepository extends JpaRepository<ClickCount, Long> {

    Optional<ClickCount> findByPcode(long pcode);
    Page<ClickCount> findAll(Pageable page);
}
