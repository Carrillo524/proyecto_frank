package com.assistancesystem.registros.domain.model.gateway;

import com.assistancesystem.registros.domain.model.Registro;

public interface RegistroGateway {
    Registro guardar(Registro registro);
}