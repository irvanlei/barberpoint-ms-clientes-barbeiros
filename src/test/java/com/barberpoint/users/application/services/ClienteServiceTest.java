package com.barberpoint.users.clientes.application.usecases;

import com.barberpoint.users.clientes.domain.entities.Cliente;
import com.barberpoint.users.clientes.domain.exceptions.ClienteException;
import com.barberpoint.users.clientes.domain.ports.ClienteRepositoryPort;
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
class CriarClienteUseCaseTest {

    @Mock
    private ClienteRepositoryPort repository;

    private CriarClienteUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new CriarClienteUseCase(repository);
    }

    @SuppressWarnings("null")
    @Test
    void deveCriarClienteComSucesso() {
        Cliente clienteEsperado = new Cliente();
        clienteEsperado.setId(1L);
        clienteEsperado.setNome("João");
        clienteEsperado.setSobrenome("Silva");
        clienteEsperado.setEmail("joao@email.com");
        clienteEsperado.setTelefone("11999999999");

        when(repository.findByEmail("joao@email.com")).thenReturn(Optional.empty());
        when(repository.save(any(Cliente.class))).thenReturn(clienteEsperado);

        var resultado = useCase.executar("João", "Silva", "joao@email.com", "11999999999");

        assertNotNull(resultado);
        assertEquals("João", resultado.getNome());
        assertEquals("joao@email.com", resultado.getEmail());
    }

    @Test
    void deveLancarExcecaoEmailDuplicado() {
        Cliente clienteExistente = new Cliente();
        clienteExistente.setEmail("existente@email.com");
        when(repository.findByEmail("existente@email.com")).thenReturn(Optional.of(clienteExistente));

        assertThrows(ClienteException.class, () ->
                useCase.executar("João", "Silva", "existente@email.com", "11999999999"));
    }

    @Test
    void deveLancarExcecaoEmailInvalido() {
        assertThrows(ClienteException.class, () ->
                useCase.executar("João", "Silva", "emailinvalido", "11999999999"));
    }

    @Test
    void deveLancarExcecaoNomeVazio() {
        assertThrows(ClienteException.class, () ->
                useCase.executar("", "Silva", "joao@email.com", "11999999999"));
    }
}
