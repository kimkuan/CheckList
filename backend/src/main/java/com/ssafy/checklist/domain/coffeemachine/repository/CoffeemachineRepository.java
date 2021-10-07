package com.ssafy.checklist.domain.coffeemachine.repository;

import com.ssafy.checklist.domain.coffeemachine.entity.Coffeemachine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoffeemachineRepository extends JpaRepository<Coffeemachine, Long> {

    Page<Coffeemachine> findAll(Pageable paging);

    Page<Coffeemachine> findAll(Specification<Coffeemachine> filter, Pageable page);

    Page<Coffeemachine> findAllByNameContaining(String keyword, Pageable page);

    long countByNameContaining(String keyword);
}
