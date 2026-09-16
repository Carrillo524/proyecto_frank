package com.assistancesystem.registros.domain.model;

import lombok.Getter;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter // Unica anotacion de Lombok recomendada aqui
public final class Registro {

    private final UUID id;
    private final UUID usuarioId;
    private final String ipOrigen;
    private final LocalDateTime fechaHora;

    // El constructor SIEMPRE debe ser manual para proteger la consistencia
    public Registro(UUID id, UUID usuarioId, String ipOrigen, LocalDateTime fechaHora) {
        this.id = Objects.requireNonNull(id, "El ID no puede ser nulo");
        this.usuarioId = Objects.requireNonNull(usuarioId, "El usuarioId no puede ser nulo");

        if (ipOrigen == null || ipOrigen.isBlank()) {
            throw new IllegalArgumentException("La IP de origen no puede estar vacia");
        }
        this.ipOrigen = ipOrigen;
        this.fechaHora = Objects.requireNonNull(fechaHora, "La fechaHora no puede ser nula");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Registro registro)) return false;
        return Objects.equals(id, registro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}