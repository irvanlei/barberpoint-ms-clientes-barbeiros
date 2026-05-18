package com.barberpoint.users.servicos.domain.validators;

import com.barberpoint.users.servicos.domain.exceptions.ServicoException;
import java.math.BigDecimal;
import java.util.regex.Pattern;

public class ServicoValidator {

    private static final Pattern NOME_PATTERN = Pattern.compile("^[A-Za-zÀ-ÖØ-öø-ÿ\\s]{3,}$");

    public static void validarCriacao(String nome, BigDecimal preco, Integer duracao) {
        if (nome == null || nome.trim().isEmpty())
            throw new ServicoException("Nome do serviço é obrigatório", "NOME_OBRIGATORIO");
        if (!NOME_PATTERN.matcher(nome).matches())
            throw new ServicoException("Nome deve ter pelo menos 3 caracteres", "NOME_MUITO_CURTO");
        if (preco == null)
            throw new ServicoException("Preço é obrigatório", "PRECO_OBRIGATORIO");
        if (preco.compareTo(BigDecimal.ZERO) <= 0)
            throw new ServicoException("Preço deve ser maior que zero", "PRECO_INVALIDO");
        if (duracao == null || duracao <= 0)
            throw new ServicoException("Duração é obrigatória e deve ser maior que 0", "DURACAO_INVALIDA");
        if (duracao > 480)
            throw new ServicoException("Duração máxima é 480 minutos (8 horas)", "DURACAO_EXCEDIDA");
    }
}
