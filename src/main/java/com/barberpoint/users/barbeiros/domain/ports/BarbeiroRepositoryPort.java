package com.barberpoint.users.barbeiros.domain.ports;

import com.barberpoint.users.barbeiros.domain.entities.Barbeiro;
import java.util.List;
import java.util.Optional;

public interface BarbeiroRepositoryPort {
    Barbeiro save(Barbeiro barbeiro);
    Optional<Barbeiro> findById(Long id);
    Optional<Barbeiro> findByEmail(String email);
    List<Barbeiro> findAll();
    void delete(Barbeiro barbeiro);
}
