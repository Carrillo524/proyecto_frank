package com.assistancesystem.registros.application.gateway;

import com.assistancesystem.registros.domain.entities.Registro;

public interface RegistroGateway {
    Registro guardar(Registro registro);
}