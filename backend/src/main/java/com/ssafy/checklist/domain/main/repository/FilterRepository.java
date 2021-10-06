package com.ssafy.checklist.domain.main.repository;

import com.ssafy.checklist.domain.main.entity.Filter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilterRepository extends JpaRepository<Filter, String> {

    Optional<Filter> findByCategory(String category);
}
