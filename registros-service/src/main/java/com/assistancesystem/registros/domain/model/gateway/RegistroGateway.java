package com.assistancesystem.registros.domain.model.gateway;

import com.assistancesystem.registros.domain.model.Registro;

import java.util.List;

public interface RegistroGateway {
    Registro guardar(Registro registro);

    List<Registro> obtenerPorGestor(String idGestor);
}
