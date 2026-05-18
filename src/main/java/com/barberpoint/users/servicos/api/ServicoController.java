package com.barberpoint.users.servicos.api;

import com.barberpoint.users.servicos.application.dtos.ServicoDTO;
import com.barberpoint.users.servicos.application.usecases.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/servicos")
@CrossOrigin(origins = "*")
public class ServicoController {

    private final CriarServicoUseCase criarUseCase;
    private final ObterServicoUseCase obterUseCase;
    private final ListarServicosUseCase listarUseCase;
    private final AtualizarServicoUseCase atualizarUseCase;
    private final DeletarServicoUseCase deletarUseCase;

    public ServicoController(CriarServicoUseCase criarUseCase, ObterServicoUseCase obterUseCase,
            ListarServicosUseCase listarUseCase, AtualizarServicoUseCase atualizarUseCase,
            DeletarServicoUseCase deletarUseCase) {
        this.criarUseCase = criarUseCase; this.obterUseCase = obterUseCase;
        this.listarUseCase = listarUseCase; this.atualizarUseCase = atualizarUseCase;
        this.deletarUseCase = deletarUseCase;
    }

    @PostMapping
    public ResponseEntity<ServicoDTO> criar(@RequestBody ServicoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                criarUseCase.executar(dto.getNome(), dto.getPreco(), dto.getDuracao()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoDTO> obter(@PathVariable Long id) {
        return ResponseEntity.ok(obterUseCase.executar(id));
    }

    @GetMapping
    public ResponseEntity<List<ServicoDTO>> listar() {
        return ResponseEntity.ok(listarUseCase.executar());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoDTO> atualizar(@PathVariable Long id, @RequestBody ServicoDTO dto) {
        return ResponseEntity.ok(atualizarUseCase.executar(id, dto.getNome(), dto.getPreco(), dto.getDuracao()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        deletarUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("MS Serviços está funcionando!");
    }
}
