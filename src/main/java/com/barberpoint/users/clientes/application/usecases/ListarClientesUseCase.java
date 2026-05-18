package com.barberpoint.users.clientes.application.usecases;

import com.barberpoint.users.clientes.application.dtos.ClienteDTO;
import com.barberpoint.users.clientes.domain.entities.Cliente;
import com.barberpoint.users.clientes.domain.ports.ClienteRepositoryPort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarClientesUseCase {

    private final ClienteRepositoryPort repository;

    public ListarClientesUseCase(ClienteRepositoryPort repository) {
        this.repository = repository;
    }

    public List<ClienteDTO> executar() {
        return repository.findAll().stream().map(this::mapear).collect(Collectors.toList());
    }

    private ClienteDTO mapear(Cliente c) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(c.getId()); dto.setNome(c.getNome());
        dto.setSobrenome(c.getSobrenome()); dto.setEmail(c.getEmail());
        dto.setTelefone(c.getTelefone()); dto.setDataCriacao(c.getDataCriacao());
        return dto;
    }
}
