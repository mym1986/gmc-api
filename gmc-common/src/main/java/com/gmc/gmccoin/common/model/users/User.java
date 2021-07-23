package com.gmc.gmccoin.common.model.users;

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
 * Users
 */
@Getter
@Setter
@Entity
@Table(name="tb_user")
@NoArgsConstructor
public class User extends Auditing implements Serializable {

    @Column(unique = true)
    private String email;
    private String password;
    private String phone;
    private String pin;
    private String recommender;
    private String userId;


    @Builder
    public User(String email, String password, String phone, String pin, String recommender, String userId) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.pin = pin;
        this.recommender = recommender;
        this.userId = userId;
    }
}