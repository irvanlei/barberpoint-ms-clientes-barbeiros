package com.barberpoint.users.barbeiros.domain.validators;

import com.barberpoint.users.barbeiros.domain.exceptions.BarbeiroException;
import java.util.regex.Pattern;

public class BarbeiroValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public static void validarCriacao(String nome, String sobrenome, String email, String telefone) {
        if (nome == null || nome.trim().isEmpty())
            throw new BarbeiroException("Nome é obrigatório", "NOME_OBRIGATORIO");
        if (nome.trim().length() < 2)
            throw new BarbeiroException("Nome deve ter pelo menos 2 caracteres", "NOME_MUITO_CURTO");
        if (sobrenome == null || sobrenome.trim().isEmpty())
            throw new BarbeiroException("Sobrenome é obrigatório", "SOBRENOME_OBRIGATORIO");
        if (email == null || email.trim().isEmpty())
            throw new BarbeiroException("Email é obrigatório", "EMAIL_OBRIGATORIO");
        if (!EMAIL_PATTERN.matcher(email).matches())
            throw new BarbeiroException("Email inválido", "EMAIL_INVALIDO");
        if (telefone == null || telefone.trim().isEmpty())
            throw new BarbeiroException("Telefone é obrigatório", "TELEFONE_OBRIGATORIO");
    }

    public static void validarAtualizacao(Long id, String nome, String sobrenome, String email, String telefone) {
        if (id == null || id <= 0)
            throw new BarbeiroException("ID inválido", "ID_INVALIDO");
        validarCriacao(nome, sobrenome, email, telefone);
    }
}
