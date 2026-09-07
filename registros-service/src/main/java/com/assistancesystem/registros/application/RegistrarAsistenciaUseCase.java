package com.assistancesystem.registros.application;

import com.assistancesystem.registros.domain.entities.Registro;
import java.util.UUID;

/**
 * Puerto de entrada (Input Port / Caso de Uso).
 * Define el contrato para registrar la asistencia de un usuario en el sistema.
 */
public interface RegistrarAsistenciaUseCase {

    /**
     * Registra la asistencia de un usuario aplicando las reglas de negocio de la aplicación.
     *
     * @param usuarioId identificador del usuario que registra asistencia
     * @param ipOrigen  dirección IP desde la que se origina la solicitud
     * @return el {@link Registro} creado y persistido
     */
    Registro registrar(UUID usuarioId, String ipOrigen);
}