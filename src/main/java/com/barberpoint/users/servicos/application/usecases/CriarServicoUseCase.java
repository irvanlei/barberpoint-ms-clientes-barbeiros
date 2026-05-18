package com.barberpoint.users.servicos.application.usecases;

import com.barberpoint.users.servicos.application.dtos.ServicoDTO;
import com.barberpoint.users.servicos.domain.entities.Servico;
import com.barberpoint.users.servicos.domain.ports.ServicoRepositoryPort;
import com.barberpoint.users.servicos.domain.validators.ServicoValidator;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class CriarServicoUseCase {

    private final ServicoRepositoryPort repository;

    public CriarServicoUseCase(ServicoRepositoryPort repository) {
        this.repository = repository;
    }

    public ServicoDTO executar(String nome, BigDecimal preco, int duracao) {
        ServicoValidator.validarCriacao(nome, preco, duracao);
        Servico servico = new Servico();
        servico.setNome(nome); servico.setPreco(preco); servico.setDuracao(duracao);
        return mapear(repository.save(servico));
    }

    private ServicoDTO mapear(Servico s) {
        ServicoDTO dto = new ServicoDTO();
        dto.setId(s.getId()); dto.setNome(s.getNome());
        dto.setPreco(s.getPreco()); dto.setDuracao(s.getDuracao());
        dto.setDataCriacao(s.getDataCriacao());
        return dto;
    }
}
