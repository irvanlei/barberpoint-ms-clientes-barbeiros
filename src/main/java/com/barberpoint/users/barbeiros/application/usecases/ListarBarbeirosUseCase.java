package com.barberpoint.users.barbeiros.application.usecases;

import com.barberpoint.users.barbeiros.application.dtos.BarbeiroDTO;
import com.barberpoint.users.barbeiros.domain.entities.Barbeiro;
import com.barberpoint.users.barbeiros.domain.ports.BarbeiroRepositoryPort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarBarbeirosUseCase {

    private final BarbeiroRepositoryPort repository;

    public ListarBarbeirosUseCase(BarbeiroRepositoryPort repository) {
        this.repository = repository;
    }

    public List<BarbeiroDTO> executar() {
        return repository.findAll().stream().map(this::mapear).collect(Collectors.toList());
    }

    private BarbeiroDTO mapear(Barbeiro b) {
        BarbeiroDTO dto = new BarbeiroDTO();
        dto.setId(b.getId()); dto.setNome(b.getNome());
        dto.setSobrenome(b.getSobrenome()); dto.setEmail(b.getEmail());
        dto.setTelefone(b.getTelefone()); dto.setDataCriacao(b.getDataCriacao());
        return dto;
    }
}
