package com.gmc.gmccoin.api.mining.repository;

import com.gmc.gmccoin.common.model.mining.Mining;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * MiningRepository
 */

@Repository
public interface MiningRepository extends JpaRepository<Mining, Long> {
    Mining findByEmail(String email);

    List<Mining> findTop100ByOrderByAmountDesc();
}
