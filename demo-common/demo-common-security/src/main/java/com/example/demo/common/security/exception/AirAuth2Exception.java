package com.example.demo.common.security.exception;

import com.example.demo.common.security.component.AirAuth2ExceptionSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @Author oyg
 * @Date 2019/5/3/22:54
 */
@JsonSerialize(using = AirAuth2ExceptionSerializer.class)
public class AirAuth2Exception extends OAuth2Exception {

    private String errorCode;

    public AirAuth2Exception(String msg) {
        super(msg);
    }

    public AirAuth2Exception(String msg, String errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
