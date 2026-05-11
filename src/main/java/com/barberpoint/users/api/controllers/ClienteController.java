package com.barberpoint.users.api.controllers;

import com.barberpoint.users.application.dtos.ClienteDTO;
import com.barberpoint.users.application.services.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> criar(@RequestBody ClienteDTO dto) {
        ClienteDTO resultado = service.criar(dto.getNome(), dto.getSobrenome(),
                dto.getEmail(), dto.getTelefone());
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obter(@PathVariable Long id) {
        ClienteDTO resultado = service.obter(id);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listar() {
        List<ClienteDTO> resultado = service.listar();
        return ResponseEntity.ok(resultado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizar(@PathVariable Long id, @RequestBody ClienteDTO dto) {
        ClienteDTO resultado = service.atualizar(id, dto.getNome(), dto.getSobrenome(),
                dto.getEmail(), dto.getTelefone());
        return ResponseEntity.ok(resultado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("MS Clientes está funcionando!");
    }
}
