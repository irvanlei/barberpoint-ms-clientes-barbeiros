package com.barberpoint.users.barbeiros.domain.exceptions;

public class BarbeiroException extends RuntimeException {
    private final String code;

    public BarbeiroException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() { return code; }
}
