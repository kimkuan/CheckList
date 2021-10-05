package com.ssafy.checklist.domain.common.repository;

import com.ssafy.checklist.domain.common.entity.LowPriceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LowPriceInfoRepository extends JpaRepository<LowPriceInfo, Long> {

    Optional<List<LowPriceInfo>> findByPcode(Long pcode);


}
