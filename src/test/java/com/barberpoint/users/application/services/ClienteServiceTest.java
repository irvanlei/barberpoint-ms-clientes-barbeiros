package com.barberpoint.users.application.services;

import com.barberpoint.users.domain.entities.Cliente;
import com.barberpoint.users.domain.exceptions.ClienteException;
import com.barberpoint.users.infrastructure.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository repository;

    private ClienteService service;

    @BeforeEach
    void setUp() {
        service = new ClienteService(repository);
    }

    @SuppressWarnings("null")
    @Test
    void devecriarClienteComSucesso() {
        // Arrange
        Cliente clienteEsperado = new Cliente();
        clienteEsperado.setId(1L);
        clienteEsperado.setNome("João");
        clienteEsperado.setSobrenome("Silva");
        clienteEsperado.setEmail("joao@email.com");
        clienteEsperado.setTelefone("11999999999");

        when(repository.findByEmail("joao@email.com")).thenReturn(Optional.empty());
        when(repository.save(any(Cliente.class))).thenReturn(clienteEsperado);

        // Act
        var resultado = service.criar("João", "Silva", "joao@email.com", "11999999999");

        // Assert
        assertNotNull(resultado);
        assertEquals("João", resultado.getNome());
        assertEquals("joao@email.com", resultado.getEmail());
    }

    @Test
    void deveLancarExcecaoEmailDuplicado() {
        // Arrange
        Cliente clienteExistente = new Cliente();
        clienteExistente.setEmail("existente@email.com");
        when(repository.findByEmail("existente@email.com")).thenReturn(Optional.of(clienteExistente));

        // Act & Assert
        assertThrows(ClienteException.class, () -> {
            service.criar("João", "Silva", "existente@email.com", "11999999999");
        });
    }

    @Test
    void deveLancarExcecaoEmailInvalido() {
        // Act & Assert
        assertThrows(ClienteException.class, () -> {
            service.criar("João", "Silva", "emailinvalido", "11999999999");
        });
    }

    @Test
    void deveLancarExcecaoNomeVazio() {
        // Act & Assert
        assertThrows(ClienteException.class, () -> {
            service.criar("", "Silva", "joao@email.com", "11999999999");
        });
    }
}
