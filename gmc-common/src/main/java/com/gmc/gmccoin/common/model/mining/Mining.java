package com.gmc.gmccoin.common.model.mining;


import com.gmc.gmccoin.common.model.commons.Auditing;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Mining
 */
@Getter
@Setter
@Entity
@Table(name="tb_mining")
@NoArgsConstructor
public class Mining extends Auditing implements Serializable {

    private String email;
    private float amount;

    @Builder
    public Mining(String email, float amount) {
        this.email = email;
        this.amount = amount;

    }
}
