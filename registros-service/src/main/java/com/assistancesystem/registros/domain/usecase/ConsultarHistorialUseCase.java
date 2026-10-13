package com.assistancesystem.registros.domain.usecase;

import com.assistancesystem.registros.domain.model.Registro;

import java.util.List;

/**
 * Puerto de entrada (Input Port / Caso de Uso) para consultar el historial de registros por gestor.
 */
public interface ConsultarHistorialUseCase {

    /**
     * Consulta el historial de registros asociados a un gestor.
     *
     * @param idGestor identificador del gestor
     * @return lista de registros encontrados ordenados por fecha descendente
     */
    List<Registro> ejecutar(String idGestor);
}
