package com.ssafy.checklist.domain.main.repository;

import com.ssafy.checklist.domain.main.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainRepository extends JpaRepository<Product, String> {
}
