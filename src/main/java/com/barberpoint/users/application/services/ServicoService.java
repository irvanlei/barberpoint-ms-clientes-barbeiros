package com.barberpoint.users.application.services;

import com.barberpoint.users.domain.entities.Servico;
import com.barberpoint.users.domain.validators.ServicoValidator;
import com.barberpoint.users.domain.exceptions.ClienteException;
import com.barberpoint.users.application.dtos.ServicoDTO;
import com.barberpoint.users.infrastructure.repository.ServicoRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicoService {

    private final ServicoRepository repository;

    public ServicoService(ServicoRepository repository) {
        this.repository = repository;
    }

    public ServicoDTO criar(String nome, BigDecimal preco, int duracao) {
        ServicoValidator.validarCriacao(nome, preco, duracao);

        Servico servico = new Servico();
        servico.setNome(nome);
        servico.setPreco(preco);
        servico.setDuracao(duracao);

        Servico salvo = repository.save(servico);
        return mapearParaDTO(salvo);
    }

    public ServicoDTO obter(Long id) {
        if (id == null || id <= 0) {
            throw new ClienteException("ID inválido", "ID_INVALIDO");
        }

        Servico servico = repository.findById(id)
                .orElseThrow(() -> new ClienteException("Serviço não encontrado", "SERVICO_NAO_ENCONTRADO"));

        return mapearParaDTO(servico);
    }

    public List<ServicoDTO> listar() {
        return repository.findAll().stream()
                .map(this::mapearParaDTO)
                .collect(Collectors.toList());
    }

    public ServicoDTO atualizar(Long id, String nome, BigDecimal preco, int duracao) {
        if (id == null || id <= 0) {
            throw new ClienteException("ID inválido", "ID_INVALIDO");
        }

        Servico servico = repository.findById(id)
                .orElseThrow(() -> new ClienteException("Serviço não encontrado", "SERVICO_NAO_ENCONTRADO"));

        if (nome != null && !nome.isBlank())
            servico.setNome(nome);
        if (preco != null && preco.compareTo(BigDecimal.ZERO) > 0)
            servico.setPreco(preco);
        if (duracao > 0)
            servico.setDuracao(duracao);

        @SuppressWarnings("null")
        Servico atualizado = repository.save(servico);
        return mapearParaDTO(atualizado);
    }

    @SuppressWarnings("null")
    public void deletar(Long id) {
        if (id == null || id <= 0) {
            throw new ClienteException("ID inválido", "ID_INVALIDO");
        }

        Servico servico = repository.findById(id)
                .orElseThrow(() -> new ClienteException("Serviço não encontrado", "SERVICO_NAO_ENCONTRADO"));

        repository.delete(servico);
    }

    private ServicoDTO mapearParaDTO(Servico servico) {
        ServicoDTO dto = new ServicoDTO();
        dto.setId(servico.getId());
        dto.setNome(servico.getNome());
        dto.setPreco(servico.getPreco());
        dto.setDuracao(servico.getDuracao());
        dto.setDataCriacao(servico.getDataCriacao());
        return dto;
    }
}
