package com.barberpoint.users.application.services;

import com.barberpoint.users.domain.entities.Barbeiro;
import com.barberpoint.users.domain.exceptions.ClienteException;
import com.barberpoint.users.domain.validators.BarbeiroValidator;
import com.barberpoint.users.application.dtos.BarbeiroDTO;
import com.barberpoint.users.infrastructure.repository.BarbeiroRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BarbeiroService {

    private final BarbeiroRepository repository;

    public BarbeiroService(BarbeiroRepository repository) {
        this.repository = repository;
    }

    public BarbeiroDTO criar(String nome, String sobrenome, String email, String telefone) {
        BarbeiroValidator.validarCriacao(nome, sobrenome, email, telefone);

        if (repository.findByEmail(email).isPresent()) {
            throw new ClienteException("Email já cadastrado", "EMAIL_DUPLICADO");
        }

        Barbeiro barbeiro = new Barbeiro();
        barbeiro.setNome(nome);
        barbeiro.setSobrenome(sobrenome);
        barbeiro.setEmail(email);
        barbeiro.setTelefone(telefone);

        Barbeiro salvo = repository.save(barbeiro);
        return mapearParaDTO(salvo);
    }

    public BarbeiroDTO obter(Long id) {
        if (id == null || id <= 0) {
            throw new ClienteException("ID inválido", "ID_INVALIDO");
        }

        Barbeiro barbeiro = repository.findById(id)
                .orElseThrow(() -> new ClienteException("Barbeiro não encontrado", "BARBEIRO_NAO_ENCONTRADO"));

        return mapearParaDTO(barbeiro);
    }

    public List<BarbeiroDTO> listar() {
        return repository.findAll().stream()
                .map(this::mapearParaDTO)
                .collect(Collectors.toList());
    }

    public BarbeiroDTO atualizar(Long id, String nome, String sobrenome, String email, String telefone) {
        if (id == null || id <= 0) {
            throw new ClienteException("ID inválido", "ID_INVALIDO");
        }

        Barbeiro barbeiro = repository.findById(id)
                .orElseThrow(() -> new ClienteException("Barbeiro não encontrado", "BARBEIRO_NAO_ENCONTRADO"));

        if (email != null && !email.isBlank()) {
            BarbeiroValidator.validarAtualizacao(id, nome, sobrenome, email, telefone);
            if (!email.equals(barbeiro.getEmail()) && repository.findByEmail(email).isPresent()) {
                throw new ClienteException("Email já cadastrado", "EMAIL_DUPLICADO");
            }
            barbeiro.setEmail(email);
        }

        if (nome != null && !nome.isBlank())
            barbeiro.setNome(nome);
        if (sobrenome != null && !sobrenome.isBlank())
            barbeiro.setSobrenome(sobrenome);
        if (telefone != null && !telefone.isBlank())
            barbeiro.setTelefone(telefone);

        @SuppressWarnings("null")
        Barbeiro atualizado = repository.save(barbeiro);
        return mapearParaDTO(atualizado);
    }

    @SuppressWarnings("null")
    public void deletar(Long id) {
        if (id == null || id <= 0) {
            throw new ClienteException("ID inválido", "ID_INVALIDO");
        }

        Barbeiro barbeiro = repository.findById(id)
                .orElseThrow(() -> new ClienteException("Barbeiro não encontrado", "BARBEIRO_NAO_ENCONTRADO"));

        repository.delete(barbeiro);
    }

    private BarbeiroDTO mapearParaDTO(Barbeiro barbeiro) {
        BarbeiroDTO dto = new BarbeiroDTO();
        dto.setId(barbeiro.getId());
        dto.setNome(barbeiro.getNome());
        dto.setSobrenome(barbeiro.getSobrenome());
        dto.setEmail(barbeiro.getEmail());
        dto.setTelefone(barbeiro.getTelefone());
        dto.setDataCriacao(barbeiro.getDataCriacao());
        return dto;
    }
}
