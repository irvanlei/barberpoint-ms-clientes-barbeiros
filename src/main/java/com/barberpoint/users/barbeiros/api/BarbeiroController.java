package com.barberpoint.users.barbeiros.api;

import com.barberpoint.users.barbeiros.application.dtos.BarbeiroDTO;
import com.barberpoint.users.barbeiros.application.usecases.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/barbeiros")
@CrossOrigin(origins = "*")
public class BarbeiroController {

    private final CriarBarbeiroUseCase criarUseCase;
    private final ObterBarbeiroUseCase obterUseCase;
    private final ListarBarbeirosUseCase listarUseCase;
    private final AtualizarBarbeiroUseCase atualizarUseCase;
    private final DeletarBarbeiroUseCase deletarUseCase;

    public BarbeiroController(CriarBarbeiroUseCase criarUseCase, ObterBarbeiroUseCase obterUseCase,
            ListarBarbeirosUseCase listarUseCase, AtualizarBarbeiroUseCase atualizarUseCase,
            DeletarBarbeiroUseCase deletarUseCase) {
        this.criarUseCase = criarUseCase; this.obterUseCase = obterUseCase;
        this.listarUseCase = listarUseCase; this.atualizarUseCase = atualizarUseCase;
        this.deletarUseCase = deletarUseCase;
    }

    @PostMapping
    public ResponseEntity<BarbeiroDTO> criar(@RequestBody BarbeiroDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                criarUseCase.executar(dto.getNome(), dto.getSobrenome(), dto.getEmail(), dto.getTelefone()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BarbeiroDTO> obter(@PathVariable Long id) {
        return ResponseEntity.ok(obterUseCase.executar(id));
    }

    @GetMapping
    public ResponseEntity<List<BarbeiroDTO>> listar() {
        return ResponseEntity.ok(listarUseCase.executar());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BarbeiroDTO> atualizar(@PathVariable Long id, @RequestBody BarbeiroDTO dto) {
        return ResponseEntity.ok(atualizarUseCase.executar(id, dto.getNome(), dto.getSobrenome(), dto.getEmail(), dto.getTelefone()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        deletarUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("MS Barbeiros está funcionando!");
    }
}
