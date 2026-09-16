package com.assistancesystem.registros.infrastructure.driver_adapters;

import com.assistancesystem.registros.domain.model.gateway.RegistroGateway;
import com.assistancesystem.registros.domain.model.Registro;
import com.assistancesystem.registros.infrastructure.driver_adapters.RegistroJpaEntity;
import com.assistancesystem.registros.infrastructure.driver_adapters.RegistroJpaRepository;
import com.assistancesystem.registros.infrastructure.mapper.RegistroMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Adaptador de salida que implementa el puerto {@link RegistroGateway}
 * apoyándose en Spring Data JPA. Utiliza {@link RegistroMapper} para traducir
 * entre la entidad pura de dominio y el modelo de infraestructura.
 */
@Component
@RequiredArgsConstructor
public class RegistroGatewayImpl implements RegistroGateway {

    private final RegistroJpaRepository registroJpaRepository;

    @Override
    public Registro guardar(Registro registro) {
        RegistroJpaEntity jpaEntity = RegistroMapper.toEntity(registro);
        RegistroJpaEntity guardada = registroJpaRepository.save(jpaEntity);
        return RegistroMapper.toDomain(guardada);
    }
}