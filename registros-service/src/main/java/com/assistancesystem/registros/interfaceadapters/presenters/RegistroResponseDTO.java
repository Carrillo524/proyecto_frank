package com.assistancesystem.registros.interfaceadapters.presenters;

import com.assistancesystem.registros.domain.entities.Registro;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO de salida (Presenter / Response) inmutable que traduce la entidad
 * de dominio Registro a una representación adecuada para la API REST.
 */
public record RegistroResponseDTO(
        UUID id,
        UUID usuarioId,
        String ipOrigen,
        LocalDateTime fechaHora
) {

    public static RegistroResponseDTO desde(Registro registro) {
        if (registro == null) {
            return null;
        }
        return new RegistroResponseDTO(
                registro.getId(),
                registro.getUsuarioId(),
                registro.getIpOrigen(),
                registro.getFechaHora()
        );
    }
}