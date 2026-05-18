package com.barberpoint.users.servicos.domain.exceptions;

public class ServicoException extends RuntimeException {
    private final String code;

    public ServicoException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() { return code; }
}
