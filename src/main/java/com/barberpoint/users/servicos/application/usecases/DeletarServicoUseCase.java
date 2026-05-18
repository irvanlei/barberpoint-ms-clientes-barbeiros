package com.barberpoint.users.servicos.application.usecases;

import com.barberpoint.users.servicos.domain.entities.Servico;
import com.barberpoint.users.servicos.domain.exceptions.ServicoException;
import com.barberpoint.users.servicos.domain.ports.ServicoRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class DeletarServicoUseCase {

    private final ServicoRepositoryPort repository;

    public DeletarServicoUseCase(ServicoRepositoryPort repository) {
        this.repository = repository;
    }

    @SuppressWarnings("null")
    public void executar(Long id) {
        if (id == null || id <= 0) throw new ServicoException("ID inválido", "ID_INVALIDO");
        Servico s = repository.findById(id)
                .orElseThrow(() -> new ServicoException("Serviço não encontrado", "SERVICO_NAO_ENCONTRADO"));
        repository.delete(s);
    }
}
