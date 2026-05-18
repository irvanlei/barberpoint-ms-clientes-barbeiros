package com.barberpoint.users.servicos.application.usecases;

import com.barberpoint.users.servicos.application.dtos.ServicoDTO;
import com.barberpoint.users.servicos.domain.entities.Servico;
import com.barberpoint.users.servicos.domain.ports.ServicoRepositoryPort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarServicosUseCase {

    private final ServicoRepositoryPort repository;

    public ListarServicosUseCase(ServicoRepositoryPort repository) {
        this.repository = repository;
    }

    public List<ServicoDTO> executar() {
        return repository.findAll().stream().map(this::mapear).collect(Collectors.toList());
    }

    private ServicoDTO mapear(Servico s) {
        ServicoDTO dto = new ServicoDTO();
        dto.setId(s.getId()); dto.setNome(s.getNome());
        dto.setPreco(s.getPreco()); dto.setDuracao(s.getDuracao());
        dto.setDataCriacao(s.getDataCriacao());
        return dto;
    }
}
