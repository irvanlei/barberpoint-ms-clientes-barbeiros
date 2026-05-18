package com.barberpoint.users.servicos.infrastructure.repository;

import com.barberpoint.users.servicos.domain.entities.Servico;
import com.barberpoint.users.servicos.domain.ports.ServicoRepositoryPort;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class ServicoRepositoryAdapter implements ServicoRepositoryPort {

    private final ServicoRepository springRepository;

    public ServicoRepositoryAdapter(ServicoRepository springRepository) {
        this.springRepository = springRepository;
    }

    @Override public Servico save(Servico servico) { return springRepository.save(servico); }
    @Override public Optional<Servico> findById(Long id) { return springRepository.findById(id); }
    @Override public List<Servico> findAll() { return springRepository.findAll(); }
    @Override public void delete(Servico servico) { springRepository.delete(servico); }
}
