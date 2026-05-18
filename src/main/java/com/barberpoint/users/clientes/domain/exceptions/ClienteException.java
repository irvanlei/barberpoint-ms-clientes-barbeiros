package com.barberpoint.users.clientes.domain.exceptions;

public class ClienteException extends RuntimeException {
    private final String code;

    public ClienteException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() { return code; }
}
