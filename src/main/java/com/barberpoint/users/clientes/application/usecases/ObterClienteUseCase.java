package com.barberpoint.users.clientes.application.usecases;

import com.barberpoint.users.clientes.application.dtos.ClienteDTO;
import com.barberpoint.users.clientes.domain.entities.Cliente;
import com.barberpoint.users.clientes.domain.exceptions.ClienteException;
import com.barberpoint.users.clientes.domain.ports.ClienteRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class ObterClienteUseCase {

    private final ClienteRepositoryPort repository;

    public ObterClienteUseCase(ClienteRepositoryPort repository) {
        this.repository = repository;
    }

    public ClienteDTO executar(Long id) {
        if (id == null || id <= 0)
            throw new ClienteException("ID inválido", "ID_INVALIDO");

        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new ClienteException("Cliente não encontrado", "CLIENTE_NAO_ENCONTRADO"));
        return mapear(cliente);
    }

    private ClienteDTO mapear(Cliente c) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(c.getId()); dto.setNome(c.getNome());
        dto.setSobrenome(c.getSobrenome()); dto.setEmail(c.getEmail());
        dto.setTelefone(c.getTelefone()); dto.setDataCriacao(c.getDataCriacao());
        return dto;
    }
}
