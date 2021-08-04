package com.gmc.gmccoin.api.mining.repository;

import com.gmc.gmccoin.common.model.mining.MiningHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * MininghistoryRepository
 */

@Repository
public interface MininghistoryRepository extends JpaRepository<MiningHistory, Long> {

    MiningHistory findTop1ByEmailAndIsCompleteOrderByIdDesc(String s, String n);

    @Transactional
    @Modifying
    @Query(value = "delete from tb_mining_history t where t.is_complete <> 'Y' and t.id <> :id", nativeQuery = true)
//    void delete(Long entityId);
    void delete(@Param("id")Long id);

    @Query(value = "select count(*) from tb_mining_history t where t.email = :email and t.is_complete = :isComplete " +
            "and to_char(t.mining_start_dt, 'YYYY-MM-DD') = to_char(CURRENT_DATE, 'YYYY-MM-DD') and is_mining = 'Y'", nativeQuery = true)
    Long selectCountByEmailAndIsComplete(@Param("email")String email, @Param("isComplete")String isComplete);

    @Query(value = "select SUM(t.mining_amount) from tb_mining_history t where t.email = :email and t.is_complete = :isComplete " +
            "and to_char(t.mining_start_dt, 'YYYY-MM-DD') = to_char(CURRENT_DATE, 'YYYY-MM-DD')", nativeQuery = true)
    float selectTodayAmountByEmailAndIsComplete(@Param("email")String email, @Param("isComplete")String isComplete);

    List<MiningHistory> findByEmailAndIsCompleteOrderByUpdatedAtDesc(String email, String isComplete);
}
