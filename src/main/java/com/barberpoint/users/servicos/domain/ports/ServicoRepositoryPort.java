package com.barberpoint.users.servicos.domain.ports;

import com.barberpoint.users.servicos.domain.entities.Servico;
import java.util.List;
import java.util.Optional;

public interface ServicoRepositoryPort {
    Servico save(Servico servico);
    Optional<Servico> findById(Long id);
    List<Servico> findAll();
    void delete(Servico servico);
}
