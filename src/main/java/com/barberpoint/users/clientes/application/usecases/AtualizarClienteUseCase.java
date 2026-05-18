package com.barberpoint.users.clientes.application.usecases;

import com.barberpoint.users.clientes.application.dtos.ClienteDTO;
import com.barberpoint.users.clientes.domain.entities.Cliente;
import com.barberpoint.users.clientes.domain.exceptions.ClienteException;
import com.barberpoint.users.clientes.domain.ports.ClienteRepositoryPort;
import com.barberpoint.users.clientes.domain.validators.ClienteValidator;
import org.springframework.stereotype.Service;

@Service
public class AtualizarClienteUseCase {

    private final ClienteRepositoryPort repository;

    public AtualizarClienteUseCase(ClienteRepositoryPort repository) {
        this.repository = repository;
    }

    @SuppressWarnings("null")
    public ClienteDTO executar(Long id, String nome, String sobrenome, String email, String telefone) {
        if (id == null || id <= 0)
            throw new ClienteException("ID inválido", "ID_INVALIDO");

        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new ClienteException("Cliente não encontrado", "CLIENTE_NAO_ENCONTRADO"));

        if (email != null && !email.isBlank()) {
            ClienteValidator.validarAtualizacao(email);
            if (!email.equals(cliente.getEmail()) && repository.findByEmail(email).isPresent())
                throw new ClienteException("Email já cadastrado", "EMAIL_DUPLICADO");
            cliente.setEmail(email);
        }
        if (nome != null && !nome.isBlank()) cliente.setNome(nome);
        if (sobrenome != null && !sobrenome.isBlank()) cliente.setSobrenome(sobrenome);
        if (telefone != null && !telefone.isBlank()) cliente.setTelefone(telefone);

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
