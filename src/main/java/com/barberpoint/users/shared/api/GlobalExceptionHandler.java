package com.barberpoint.users.shared.api;

import com.barberpoint.users.barbeiros.domain.exceptions.BarbeiroException;
import com.barberpoint.users.clientes.domain.exceptions.ClienteException;
import com.barberpoint.users.servicos.domain.exceptions.ServicoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Handler global de exceções — código compartilhado entre todos os slices.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClienteException.class)
    public ResponseEntity<Map<String, Object>> handleClienteException(ClienteException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "Erro de Cliente", ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(BarbeiroException.class)
    public ResponseEntity<Map<String, Object>> handleBarbeiroException(BarbeiroException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "Erro de Barbeiro", ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(ServicoException.class)
    public ResponseEntity<Map<String, Object>> handleServicoException(ServicoException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "Erro de Serviço", ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro Interno do Servidor", null, ex.getMessage());
    }

    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String error,
            String code, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", error);
        if (code != null) body.put("code", code);
        body.put("message", message);
        return new ResponseEntity<>(body, status);
    }
}
