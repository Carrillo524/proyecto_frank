package com.assistancesystem.registros.application.gateway;

/**
 * Puerto de salida (output port) que abstrae la validación de la IP de
 * origen. La lógica real de validación (formato, rangos permitidos,
 * listas blancas/negras, etc.) se implementa en interfaceadapters.
 */
public interface RedGateway {

    /**
     * Indica si la IP de origen dada es válida para registrar asistencia.
     */
    boolean esIpValida(String ipOrigen);
}
