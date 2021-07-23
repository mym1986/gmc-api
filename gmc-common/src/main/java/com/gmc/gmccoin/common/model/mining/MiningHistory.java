package com.gmc.gmccoin.common.model.mining;

import com.gmc.gmccoin.common.model.commons.Auditing;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * MiningHistory
 */
@Getter
@Setter
@Entity
@Table(name="tb_mining_history")
@NoArgsConstructor
public class MiningHistory extends Auditing implements Serializable {
    private String email;
    private float miningAmount;
    private LocalDateTime miningStartDt;
    private String isComplete;

    @Builder
    public MiningHistory(String email, float miningAmount,LocalDateTime miningStartDt, String isComplete) {
        this.email = email;
        this.miningAmount = miningAmount;
        this.miningStartDt = miningStartDt;
        this.isComplete = isComplete;

    }
}
