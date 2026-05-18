package com.barberpoint.users.clientes.application.usecases;

import com.barberpoint.users.clientes.application.dtos.ClienteDTO;
import com.barberpoint.users.clientes.domain.entities.Cliente;
import com.barberpoint.users.clientes.domain.exceptions.ClienteException;
import com.barberpoint.users.clientes.domain.ports.ClienteRepositoryPort;
import com.barberpoint.users.clientes.domain.validators.ClienteValidator;
import org.springframework.stereotype.Service;

@Service
public class CriarClienteUseCase {

    private final ClienteRepositoryPort repository;

    public CriarClienteUseCase(ClienteRepositoryPort repository) {
        this.repository = repository;
    }

    public ClienteDTO executar(String nome, String sobrenome, String email, String telefone) {
        ClienteValidator.validarCriacao(nome, sobrenome, email, telefone);

        if (repository.findByEmail(email).isPresent())
            throw new ClienteException("Email já cadastrado", "EMAIL_DUPLICADO");

        Cliente cliente = new Cliente();
        cliente.setNome(nome); cliente.setSobrenome(sobrenome);
        cliente.setEmail(email); cliente.setTelefone(telefone);

        return mapear(repository.save(cliente));
    }

    private ClienteDTO mapear(Cliente c) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(c.getId()); dto.setNome(c.getNome());
        dto.setSobrenome(c.getSobrenome()); dto.setEmail(c.getEmail());
        dto.setTelefone(c.getTelefone()); dto.setDataCriacao(c.getDataCriacao());
        return dto;
    }
}
