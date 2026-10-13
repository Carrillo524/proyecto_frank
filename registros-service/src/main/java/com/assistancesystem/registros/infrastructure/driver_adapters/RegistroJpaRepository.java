package com.assistancesystem.registros.infrastructure.driver_adapters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repositorio Spring Data JPA para la entidad {@link RegistroJpaEntity}.
 */
@Repository
public interface RegistroJpaRepository extends JpaRepository<RegistroJpaEntity, UUID> {

    List<RegistroJpaEntity> findByIdGestorOrderByFechaDesc(String idGestor);
}


