package com.barberpoint.users.domain.validators;

import com.barberpoint.users.domain.exceptions.ClienteException;
import java.util.regex.Pattern;

public class ClienteValidator {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public static void validarCriacao(String nome, String sobrenome, String email, String telefone) {
        if (nome == null || nome.isBlank()) {
            throw new ClienteException("Nome é obrigatório", "NOME_OBRIGATORIO");
        }
        if (sobrenome == null || sobrenome.isBlank()) {
            throw new ClienteException("Sobrenome é obrigatório", "SOBRENOME_OBRIGATORIO");
        }
        if (email == null || email.isBlank()) {
            throw new ClienteException("Email é obrigatório", "EMAIL_OBRIGATORIO");
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new ClienteException("Email inválido", "EMAIL_INVALIDO");
        }
        if (telefone == null || telefone.isBlank()) {
            throw new ClienteException("Telefone é obrigatório", "TELEFONE_OBRIGATORIO");
        }
    }

    public static void validarAtualizacao(String email) {
        if (email != null && !email.isBlank() && !EMAIL_PATTERN.matcher(email).matches()) {
            throw new ClienteException("Email inválido", "EMAIL_INVALIDO");
        }
    }
}
