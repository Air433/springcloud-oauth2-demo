package com.example.demo.common.core.exception;

/**
 * @Author oyg
 * @Date 2019/5/13/21:07
 */
public class AirDeniedException extends RuntimeException {

    public AirDeniedException(String message) {
        super(message);
    }

    public AirDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AirDeniedException(Throwable cause) {
        super(cause);
    }

    public AirDeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
