package com.barberpoint.users.servicos.infrastructure.repository;

import com.barberpoint.users.servicos.domain.entities.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
}
