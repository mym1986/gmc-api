package com.gmc.gmccoin.common.model.commons;

import lombok.Getter;

@Getter
public enum UserType {

    STATUS1(1, ""),
    STATUS2(2, "정상"),
    STATUS3(3, "탈퇴");

    private int code;
    private String message;

    public static String getMessage(int code) {
        String res = null;
        switch (code) {
            case 2 : res = STATUS2.message; break;
            case 3 : res = STATUS3.message; break;
        }
        return res;
    }

    UserType(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
