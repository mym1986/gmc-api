package com.gmc.gmccoin.api.users.dto;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
public class SignDTO implements Serializable {
    @Email(message =  "invalid email.")
    private String email;
    //^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-z])(?=.*[A-Z]).{9,12}$
    //@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[a-zA-Z\\d]{8,}$" , message = "Invalid password.")
    private String password;
    private String confirmPassword;
    private String phone;
    private String oldPassword;
    private String pin;
    private String recommender;
    private String userId;

    @Builder
    public SignDTO(@Email(message = "invalid email.") String email,
                   /*@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Invalid password.") */String password,
                   String confirmPassword, String phone,String oldPassword, String pin, String recommender, String userId) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.phone = phone;
        this.oldPassword = oldPassword;
        this.pin = pin;
        this.recommender = recommender;
        this.userId = userId;
    }
}
