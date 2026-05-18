package com.barberpoint.users.barbeiros.infrastructure.repository;

import com.barberpoint.users.barbeiros.domain.entities.Barbeiro;
import com.barberpoint.users.barbeiros.domain.ports.BarbeiroRepositoryPort;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class BarbeiroRepositoryAdapter implements BarbeiroRepositoryPort {

    private final BarbeiroRepository springRepository;

    public BarbeiroRepositoryAdapter(BarbeiroRepository springRepository) {
        this.springRepository = springRepository;
    }

    @Override public Barbeiro save(Barbeiro barbeiro) { return springRepository.save(barbeiro); }
    @Override public Optional<Barbeiro> findById(Long id) { return springRepository.findById(id); }
    @Override public Optional<Barbeiro> findByEmail(String email) { return springRepository.findByEmail(email); }
    @Override public List<Barbeiro> findAll() { return springRepository.findAll(); }
    @Override public void delete(Barbeiro barbeiro) { springRepository.delete(barbeiro); }
}
