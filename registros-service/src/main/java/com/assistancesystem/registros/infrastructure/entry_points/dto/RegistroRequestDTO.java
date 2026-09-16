package com.assistancesystem.registros.infrastructure.entry_points.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * DTO de entrada (request) inmutable para registrar asistencia vía REST.
 */
public record RegistroRequestDTO(
        @NotNull(message = "usuarioId es obligatorio")
        UUID usuarioId,

        @NotBlank(message = "ipOrigen es obligatorio")
        String ipOrigen
) {}