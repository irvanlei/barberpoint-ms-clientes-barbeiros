package com.barberpoint.users.clientes.application.usecases;

import com.barberpoint.users.clientes.domain.entities.Cliente;
import com.barberpoint.users.clientes.domain.exceptions.ClienteException;
import com.barberpoint.users.clientes.domain.ports.ClienteRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class DeletarClienteUseCase {

    private final ClienteRepositoryPort repository;

    public DeletarClienteUseCase(ClienteRepositoryPort repository) {
        this.repository = repository;
    }

    @SuppressWarnings("null")
    public void executar(Long id) {
        if (id == null || id <= 0)
            throw new ClienteException("ID inválido", "ID_INVALIDO");

        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new ClienteException("Cliente não encontrado", "CLIENTE_NAO_ENCONTRADO"));
        repository.delete(cliente);
    }
}
