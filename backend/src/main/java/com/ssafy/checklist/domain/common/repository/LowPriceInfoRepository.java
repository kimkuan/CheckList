package com.ssafy.checklist.domain.common.repository;

import com.ssafy.checklist.domain.common.entity.LowPriceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LowPriceInfoRepository extends JpaRepository<LowPriceInfo, Long> {

}
