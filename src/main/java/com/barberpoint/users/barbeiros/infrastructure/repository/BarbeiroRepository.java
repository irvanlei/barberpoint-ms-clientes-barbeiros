package com.barberpoint.users.barbeiros.infrastructure.repository;

import com.barberpoint.users.barbeiros.domain.entities.Barbeiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> {
    Optional<Barbeiro> findByEmail(String email);
}
