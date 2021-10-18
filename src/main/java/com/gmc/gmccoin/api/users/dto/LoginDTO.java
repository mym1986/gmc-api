package com.gmc.gmccoin.api.users.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
public class LoginDTO {
    @Email(message =  "invalid email.")
    private String email;
    //^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-z])(?=.*[A-Z]).{9,12}$
    //@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[a-zA-Z\\d]{8,}$" , message = "Invalid password.")
    private String password;
}
