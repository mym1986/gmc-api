package com.gmc.gmccoin.common.dto.commons;

import com.gmc.gmccoin.common.common.exception.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * R
 */

@Getter
@ToString
public class Response<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private ErrorCode errorCode;
    private T data;
  
    @Builder
    public Response(T data, ErrorCode errorCode) {
      this.data = data;
      this.errorCode = errorCode;
    }
}