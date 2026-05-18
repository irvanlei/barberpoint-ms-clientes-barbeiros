package com.barberpoint.users.barbeiros.application.usecases;

import com.barberpoint.users.barbeiros.domain.entities.Barbeiro;
import com.barberpoint.users.barbeiros.domain.exceptions.BarbeiroException;
import com.barberpoint.users.barbeiros.domain.ports.BarbeiroRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class DeletarBarbeiroUseCase {

    private final BarbeiroRepositoryPort repository;

    public DeletarBarbeiroUseCase(BarbeiroRepositoryPort repository) {
        this.repository = repository;
    }

    @SuppressWarnings("null")
    public void executar(Long id) {
        if (id == null || id <= 0) throw new BarbeiroException("ID inválido", "ID_INVALIDO");
        Barbeiro b = repository.findById(id)
                .orElseThrow(() -> new BarbeiroException("Barbeiro não encontrado", "BARBEIRO_NAO_ENCONTRADO"));
        repository.delete(b);
    }
}
