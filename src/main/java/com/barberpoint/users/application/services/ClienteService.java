package com.barberpoint.users.application.services;

import com.barberpoint.users.domain.entities.Cliente;
import com.barberpoint.users.domain.exceptions.ClienteException;
import com.barberpoint.users.domain.validators.ClienteValidator;
import com.barberpoint.users.application.dtos.ClienteDTO;
import com.barberpoint.users.infrastructure.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public ClienteDTO criar(String nome, String sobrenome, String email, String telefone) {
        ClienteValidator.validarCriacao(nome, sobrenome, email, telefone);

        // Verificar se email já existe
        if (repository.findByEmail(email).isPresent()) {
            throw new ClienteException("Email já cadastrado", "EMAIL_DUPLICADO");
        }

        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setSobrenome(sobrenome);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);

        Cliente salvo = repository.save(cliente);
        return mapearParaDTO(salvo);
    }

    public ClienteDTO obter(Long id) {
        if (id == null || id <= 0) {
            throw new ClienteException("ID inválido", "ID_INVALIDO");
        }

        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new ClienteException("Cliente não encontrado", "CLIENTE_NAO_ENCONTRADO"));

        return mapearParaDTO(cliente);
    }

    public List<ClienteDTO> listar() {
        return repository.findAll().stream()
                .map(this::mapearParaDTO)
                .collect(Collectors.toList());
    }

    public ClienteDTO atualizar(Long id, String nome, String sobrenome, String email, String telefone) {
        if (id == null || id <= 0) {
            throw new ClienteException("ID inválido", "ID_INVALIDO");
        }

        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new ClienteException("Cliente não encontrado", "CLIENTE_NAO_ENCONTRADO"));

        if (email != null && !email.isBlank()) {
            ClienteValidator.validarAtualizacao(email);
            if (!email.equals(cliente.getEmail()) && repository.findByEmail(email).isPresent()) {
                throw new ClienteException("Email já cadastrado", "EMAIL_DUPLICADO");
            }
            cliente.setEmail(email);
        }

        if (nome != null && !nome.isBlank())
            cliente.setNome(nome);
        if (sobrenome != null && !sobrenome.isBlank())
            cliente.setSobrenome(sobrenome);
        if (telefone != null && !telefone.isBlank())
            cliente.setTelefone(telefone);

        @SuppressWarnings("null")
        Cliente atualizado = repository.save(cliente);
        return mapearParaDTO(atualizado);
    }

    @SuppressWarnings("null")
    public void deletar(Long id) {
        if (id == null || id <= 0) {
            throw new ClienteException("ID inválido", "ID_INVALIDO");
        }

        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new ClienteException("Cliente não encontrado", "CLIENTE_NAO_ENCONTRADO"));

        repository.delete(cliente);
    }

    private ClienteDTO mapearParaDTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setSobrenome(cliente.getSobrenome());
        dto.setEmail(cliente.getEmail());
        dto.setTelefone(cliente.getTelefone());
        dto.setDataCriacao(cliente.getDataCriacao());
        return dto;
    }
}
