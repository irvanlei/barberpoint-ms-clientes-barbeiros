package com.barberpoint.users.clientes.api;

import com.barberpoint.users.clientes.application.dtos.ClienteDTO;
import com.barberpoint.users.clientes.application.usecases.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    private final CriarClienteUseCase criarUseCase;
    private final ObterClienteUseCase obterUseCase;
    private final ListarClientesUseCase listarUseCase;
    private final AtualizarClienteUseCase atualizarUseCase;
    private final DeletarClienteUseCase deletarUseCase;

    public ClienteController(CriarClienteUseCase criarUseCase, ObterClienteUseCase obterUseCase,
            ListarClientesUseCase listarUseCase, AtualizarClienteUseCase atualizarUseCase,
            DeletarClienteUseCase deletarUseCase) {
        this.criarUseCase = criarUseCase; this.obterUseCase = obterUseCase;
        this.listarUseCase = listarUseCase; this.atualizarUseCase = atualizarUseCase;
        this.deletarUseCase = deletarUseCase;
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> criar(@RequestBody ClienteDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                criarUseCase.executar(dto.getNome(), dto.getSobrenome(), dto.getEmail(), dto.getTelefone()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obter(@PathVariable Long id) {
        return ResponseEntity.ok(obterUseCase.executar(id));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listar() {
        return ResponseEntity.ok(listarUseCase.executar());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizar(@PathVariable Long id, @RequestBody ClienteDTO dto) {
        return ResponseEntity.ok(atualizarUseCase.executar(id, dto.getNome(), dto.getSobrenome(), dto.getEmail(), dto.getTelefone()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        deletarUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("MS Clientes está funcionando!");
    }
}
