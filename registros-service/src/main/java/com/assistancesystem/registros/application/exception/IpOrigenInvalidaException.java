package com.assistancesystem.registros.application.exception;

import com.assistancesystem.registros.application.gateway.RedGateway;

/**
 * Excepción de negocio lanzada por el interactor cuando la IP de origen
 * no es válida según {@link RedGateway}.
 */
public class IpOrigenInvalidaException extends RuntimeException {

    public IpOrigenInvalidaException(String ipOrigen) {
        super("La IP de origen '" + ipOrigen + "' no es válida para registrar asistencia");
    }
}
