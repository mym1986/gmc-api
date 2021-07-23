package com.gmc.gmccoin.common.dto.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.gmc.gmccoin.common.dto.commons.BaseDTO;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.io.Serializable;

/**
 * UserDTO
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO extends BaseDTO implements Serializable{
    private String email;
    @JsonIgnore
    private String password;
    private String phone;
    private String pin;
    private String recommender;
    private String userId;

    @Builder
    @QueryProjection
    public UserDTO(String email, String password, String phone, String pin, String recommender, String userId) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.pin = pin;
        this.recommender = recommender;
        this.userId = userId;
    }
}