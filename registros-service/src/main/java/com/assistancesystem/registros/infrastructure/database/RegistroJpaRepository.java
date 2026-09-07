package com.assistancesystem.registros.infrastructure.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repositorio Spring Data JPA para la entidad {@link RegistroJpaEntity}.
 */
@Repository
public interface RegistroJpaRepository extends JpaRepository<RegistroJpaEntity, UUID> {
}
