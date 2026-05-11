package com.barberpoint.users.api.controllers;

import com.barberpoint.users.application.dtos.ServicoDTO;
import com.barberpoint.users.application.services.ServicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos")
@CrossOrigin(origins = "*")
public class ServicoController {

    private final ServicoService service;

    public ServicoController(ServicoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ServicoDTO> criar(@RequestBody ServicoDTO dto) {
        ServicoDTO resultado = service.criar(dto.getNome(), dto.getPreco(), dto.getDuracao());
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoDTO> obter(@PathVariable Long id) {
        ServicoDTO resultado = service.obter(id);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping
    public ResponseEntity<List<ServicoDTO>> listar() {
        List<ServicoDTO> resultado = service.listar();
        return ResponseEntity.ok(resultado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoDTO> atualizar(@PathVariable Long id, @RequestBody ServicoDTO dto) {
        ServicoDTO resultado = service.atualizar(id, dto.getNome(), dto.getPreco(), dto.getDuracao());
        return ResponseEntity.ok(resultado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("MS Serviços está funcionando!");
    }
}
