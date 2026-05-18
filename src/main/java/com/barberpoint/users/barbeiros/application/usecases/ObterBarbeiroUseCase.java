package com.barberpoint.users.barbeiros.application.usecases;

import com.barberpoint.users.barbeiros.application.dtos.BarbeiroDTO;
import com.barberpoint.users.barbeiros.domain.entities.Barbeiro;
import com.barberpoint.users.barbeiros.domain.exceptions.BarbeiroException;
import com.barberpoint.users.barbeiros.domain.ports.BarbeiroRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class ObterBarbeiroUseCase {

    private final BarbeiroRepositoryPort repository;

    public ObterBarbeiroUseCase(BarbeiroRepositoryPort repository) {
        this.repository = repository;
    }

    public BarbeiroDTO executar(Long id) {
        if (id == null || id <= 0) throw new BarbeiroException("ID inválido", "ID_INVALIDO");
        Barbeiro b = repository.findById(id)
                .orElseThrow(() -> new BarbeiroException("Barbeiro não encontrado", "BARBEIRO_NAO_ENCONTRADO"));
        return mapear(b);
    }

    private BarbeiroDTO mapear(Barbeiro b) {
        BarbeiroDTO dto = new BarbeiroDTO();
        dto.setId(b.getId()); dto.setNome(b.getNome());
        dto.setSobrenome(b.getSobrenome()); dto.setEmail(b.getEmail());
        dto.setTelefone(b.getTelefone()); dto.setDataCriacao(b.getDataCriacao());
        return dto;
    }
}
