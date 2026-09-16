package com.assistancesystem.registros.infrastructure.mapper;

import com.assistancesystem.registros.domain.model.Registro;
import com.assistancesystem.registros.infrastructure.driver_adapters.RegistroJpaEntity;

public final class RegistroMapper {

    private RegistroMapper() {
        // Clase de utilidad no instanciable
    }

    public static RegistroJpaEntity toEntity(Registro domain) {
        if (domain == null) return null;
        return new RegistroJpaEntity(
                domain.getId(),
                domain.getUsuarioId(),
                domain.getIpOrigen(),
                domain.getFechaHora()
        );
    }

    public static Registro toDomain(RegistroJpaEntity entity) {
        if (entity == null) return null;
        return new Registro(
                entity.getId(),
                entity.getUsuarioId(),
                entity.getIpOrigen(),
                entity.getFechaHora()
        );
    }
}