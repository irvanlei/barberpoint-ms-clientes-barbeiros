package com.barberpoint.users.api.controllers;

import com.barberpoint.users.application.dtos.BarbeiroDTO;
import com.barberpoint.users.application.services.BarbeiroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/barbeiros")
@CrossOrigin(origins = "*")
public class BarbeiroController {

    private final BarbeiroService service;

    public BarbeiroController(BarbeiroService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BarbeiroDTO> criar(@RequestBody BarbeiroDTO dto) {
        BarbeiroDTO resultado = service.criar(dto.getNome(), dto.getSobrenome(),
                dto.getEmail(), dto.getTelefone());
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BarbeiroDTO> obter(@PathVariable Long id) {
        BarbeiroDTO resultado = service.obter(id);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping
    public ResponseEntity<List<BarbeiroDTO>> listar() {
        List<BarbeiroDTO> resultado = service.listar();
        return ResponseEntity.ok(resultado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BarbeiroDTO> atualizar(@PathVariable Long id, @RequestBody BarbeiroDTO dto) {
        BarbeiroDTO resultado = service.atualizar(id, dto.getNome(), dto.getSobrenome(),
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
        return ResponseEntity.ok("MS Barbeiros está funcionando!");
    }
}
