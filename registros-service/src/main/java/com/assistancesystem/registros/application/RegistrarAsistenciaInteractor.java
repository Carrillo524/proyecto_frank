package com.assistancesystem.registros.application;

import com.assistancesystem.registros.application.gateway.RedGateway;
import com.assistancesystem.registros.application.gateway.RegistroGateway;
import com.assistancesystem.registros.domain.entities.Registro;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
public class RegistrarAsistenciaInteractor implements RegistrarAsistenciaUseCase {

    private final RegistroGateway registroGateway;
    private final RedGateway redGateway;

    @Override
    public Registro registrar(UUID usuarioId, String ipOrigen) {
        // 1. Regla de negocio: Validar IP
        if (!redGateway.esIpValida(ipOrigen)) {
            throw new IpOrigenInvalidaException(ipOrigen);
        }

        // 2. Crear la entidad de dominio de forma explícita y determinista
        Registro registro = new Registro(
                UUID.randomUUID(),
                usuarioId,
                ipOrigen,
                LocalDateTime.now()
        );

        // 3. Persistir
        return registroGateway.guardar(registro);
    }
}