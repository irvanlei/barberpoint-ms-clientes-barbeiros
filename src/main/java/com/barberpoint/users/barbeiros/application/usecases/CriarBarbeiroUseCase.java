package com.barberpoint.users.barbeiros.application.usecases;

import com.barberpoint.users.barbeiros.application.dtos.BarbeiroDTO;
import com.barberpoint.users.barbeiros.domain.entities.Barbeiro;
import com.barberpoint.users.barbeiros.domain.exceptions.BarbeiroException;
import com.barberpoint.users.barbeiros.domain.ports.BarbeiroRepositoryPort;
import com.barberpoint.users.barbeiros.domain.validators.BarbeiroValidator;
import org.springframework.stereotype.Service;

@Service
public class CriarBarbeiroUseCase {

    private final BarbeiroRepositoryPort repository;

    public CriarBarbeiroUseCase(BarbeiroRepositoryPort repository) {
        this.repository = repository;
    }

    public BarbeiroDTO executar(String nome, String sobrenome, String email, String telefone) {
        BarbeiroValidator.validarCriacao(nome, sobrenome, email, telefone);
        if (repository.findByEmail(email).isPresent())
            throw new BarbeiroException("Email já cadastrado", "EMAIL_DUPLICADO");

        Barbeiro barbeiro = new Barbeiro();
        barbeiro.setNome(nome); barbeiro.setSobrenome(sobrenome);
        barbeiro.setEmail(email); barbeiro.setTelefone(telefone);
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
