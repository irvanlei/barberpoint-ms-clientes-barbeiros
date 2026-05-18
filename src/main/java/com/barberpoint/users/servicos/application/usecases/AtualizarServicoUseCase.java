package com.barberpoint.users.servicos.application.usecases;

import com.barberpoint.users.servicos.application.dtos.ServicoDTO;
import com.barberpoint.users.servicos.domain.entities.Servico;
import com.barberpoint.users.servicos.domain.exceptions.ServicoException;
import com.barberpoint.users.servicos.domain.ports.ServicoRepositoryPort;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class AtualizarServicoUseCase {

    private final ServicoRepositoryPort repository;

    public AtualizarServicoUseCase(ServicoRepositoryPort repository) {
        this.repository = repository;
    }

    @SuppressWarnings("null")
    public ServicoDTO executar(Long id, String nome, BigDecimal preco, int duracao) {
        if (id == null || id <= 0) throw new ServicoException("ID inválido", "ID_INVALIDO");
        Servico servico = repository.findById(id)
                .orElseThrow(() -> new ServicoException("Serviço não encontrado", "SERVICO_NAO_ENCONTRADO"));

        if (nome != null && !nome.isBlank()) servico.setNome(nome);
        if (preco != null && preco.compareTo(BigDecimal.ZERO) > 0) servico.setPreco(preco);
        if (duracao > 0) servico.setDuracao(duracao);

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
