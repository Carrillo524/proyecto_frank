package com.assistancesystem.registros.domain.usecase;

import com.assistancesystem.registros.domain.model.Registro;
import com.assistancesystem.registros.domain.model.gateway.RegistroGateway;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Implementación del caso de uso (Interactor) para consultar el historial de registros por gestor.
 * Depende únicamente del puerto de salida {@link RegistroGateway}.
 */
@RequiredArgsConstructor
public class ConsultarHistorialInteractor implements ConsultarHistorialUseCase {

    private final RegistroGateway registroGateway;

    @Override
    public List<Registro> ejecutar(String idGestor) {
        return registroGateway.obtenerPorGestor(idGestor);
    }
}
