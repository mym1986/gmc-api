package com.gmc.gmccoin.common.model.address;

import com.gmc.gmccoin.common.model.commons.Auditing;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Address
 */
@Getter
@Setter
@Entity
@Table(name="tb_address")
@NoArgsConstructor
public class Address extends Auditing implements Serializable {
    @Column(unique = true)
    private String email;
    private String privateKey;
    private String address;
    private String hexAddress;


    @Builder
    public Address(String email, String privateKey, String address, String hexAddress) {
        this.email = email;
        this.privateKey = privateKey;
        this.address = address;
        this.hexAddress = hexAddress;
    }
}
