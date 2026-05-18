package com.barberpoint.users.clientes.infrastructure.repository;

import com.barberpoint.users.clientes.domain.entities.Cliente;
import com.barberpoint.users.clientes.domain.ports.ClienteRepositoryPort;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class ClienteRepositoryAdapter implements ClienteRepositoryPort {

    private final ClienteRepository springRepository;

    public ClienteRepositoryAdapter(ClienteRepository springRepository) {
        this.springRepository = springRepository;
    }

    @Override public Cliente save(Cliente cliente) { return springRepository.save(cliente); }
    @Override public Optional<Cliente> findById(Long id) { return springRepository.findById(id); }
    @Override public Optional<Cliente> findByEmail(String email) { return springRepository.findByEmail(email); }
    @Override public List<Cliente> findAll() { return springRepository.findAll(); }
    @Override public void delete(Cliente cliente) { springRepository.delete(cliente); }
}
