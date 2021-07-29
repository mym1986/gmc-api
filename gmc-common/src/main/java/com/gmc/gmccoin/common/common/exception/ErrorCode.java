package com.gmc.gmccoin.common.common.exception;

import lombok.Getter;

import javax.servlet.http.HttpServletResponse;

@Getter
public enum ErrorCode {
    DATA_NOT_FOUND("ER_0001", "데이터를 찾을수 없습니다.", HttpServletResponse.SC_BAD_REQUEST),
    NO_CHANGE_DATA("ER_0002", "수정된 데이터가 없습니다.", HttpServletResponse.SC_INTERNAL_SERVER_ERROR),
    BAD_REQUEST("ER_0003", "잘못된 요청입니다.", HttpServletResponse.SC_BAD_REQUEST),
    INTERNAL_SERVER_ERROR("ER_9999", "오류가 발생 했습니다.", HttpServletResponse.SC_INTERNAL_SERVER_ERROR),

    //USER 100
    NOT_MATCH("ER_0100", "이미 가입된 이메일입니다.", HttpServletResponse.SC_INTERNAL_SERVER_ERROR),
    ID_NOT_FOUND("ER_0101", "이메일을 입력해 주십시오.", HttpServletResponse.SC_INTERNAL_SERVER_ERROR),
    PW_NOT_FOUND("ER_0102", "비밀번호가 일치하지 않습니다.", HttpServletResponse.SC_INTERNAL_SERVER_ERROR),
    ID_NOT_MATCH("ER_0103", "이미 가입된 ID입니다.", HttpServletResponse.SC_INTERNAL_SERVER_ERROR),
    ;

    private final String code;
    private final String message;
    private final int status;

    ErrorCode(String code, String message, int status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}