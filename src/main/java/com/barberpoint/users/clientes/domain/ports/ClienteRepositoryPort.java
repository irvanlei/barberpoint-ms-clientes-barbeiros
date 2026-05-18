package com.barberpoint.users.clientes.domain.ports;

import com.barberpoint.users.clientes.domain.entities.Cliente;
import java.util.List;
import java.util.Optional;

/**
 * Porta de repositório do domínio de clientes (Clean Architecture).
 */
public interface ClienteRepositoryPort {
    Cliente save(Cliente cliente);
    Optional<Cliente> findById(Long id);
    Optional<Cliente> findByEmail(String email);
    List<Cliente> findAll();
    void delete(Cliente cliente);
}
