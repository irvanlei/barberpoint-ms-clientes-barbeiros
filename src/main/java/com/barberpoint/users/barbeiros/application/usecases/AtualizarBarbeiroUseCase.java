package com.barberpoint.users.barbeiros.application.usecases;

import com.barberpoint.users.barbeiros.application.dtos.BarbeiroDTO;
import com.barberpoint.users.barbeiros.domain.entities.Barbeiro;
import com.barberpoint.users.barbeiros.domain.exceptions.BarbeiroException;
import com.barberpoint.users.barbeiros.domain.ports.BarbeiroRepositoryPort;
import com.barberpoint.users.barbeiros.domain.validators.BarbeiroValidator;
import org.springframework.stereotype.Service;

@Service
public class AtualizarBarbeiroUseCase {

    private final BarbeiroRepositoryPort repository;

    public AtualizarBarbeiroUseCase(BarbeiroRepositoryPort repository) {
        this.repository = repository;
    }

    @SuppressWarnings("null")
    public BarbeiroDTO executar(Long id, String nome, String sobrenome, String email, String telefone) {
        if (id == null || id <= 0) throw new BarbeiroException("ID inválido", "ID_INVALIDO");

        Barbeiro barbeiro = repository.findById(id)
                .orElseThrow(() -> new BarbeiroException("Barbeiro não encontrado", "BARBEIRO_NAO_ENCONTRADO"));

        if (email != null && !email.isBlank()) {
            BarbeiroValidator.validarAtualizacao(id, nome, sobrenome, email, telefone);
            if (!email.equals(barbeiro.getEmail()) && repository.findByEmail(email).isPresent())
                throw new BarbeiroException("Email já cadastrado", "EMAIL_DUPLICADO");
            barbeiro.setEmail(email);
        }
        if (nome != null && !nome.isBlank()) barbeiro.setNome(nome);
        if (sobrenome != null && !sobrenome.isBlank()) barbeiro.setSobrenome(sobrenome);
        if (telefone != null && !telefone.isBlank()) barbeiro.setTelefone(telefone);

        return mapear(repository.save(barbeiro));
    }

    private BarbeiroDTO mapear(Barbeiro b) {
        BarbeiroDTO dto = new BarbeiroDTO();
        dto.setId(b.getId()); dto.setNome(b.getNome());
        dto.setSobrenome(b.getSobrenome()); dto.setEmail(b.getEmail());
        dto.setTelefone(b.getTelefone()); dto.setDataCriacao(b.getDataCriacao());
        return dto;
    }
}
